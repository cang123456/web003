package com.example.wms001.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    // 从配置文件读取密钥（application.yml 配置不变）
    @Value("${jwt.secret}")
    private String secret;

    // Token过期时间（秒）
    @Value("${jwt.expire-time}")
    private Long expireTime;

    // 生成密钥
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 生成Token（逻辑不变）
    public String generateToken(String userId, Map<String, Object> claims) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expireTime * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 解析Token（逻辑不变）
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token已过期");
        } catch (JwtException e) {
            throw new RuntimeException("Token无效");
        }
    }

    // 获取用户ID（逻辑不变）
    public String getUserIdFromToken(String token) {
        return parseToken(token).getSubject();
    }

    // 验证Token（逻辑不变）
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}