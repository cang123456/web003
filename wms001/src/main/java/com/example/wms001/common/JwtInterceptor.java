package com.example.wms001.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest; // 关键：jakarta而非javax
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil; // 注入common包的JwtUtil

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 排除登录接口
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/user/")  || requestURI.contains("/menu") || 1==1) {
            return true;
        }

        // 获取Token
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("请先登录");
        }
        token = token.replace("Bearer ", "");

        // 验证Token
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Token无效或已过期");
        }

        // 存入用户ID到请求域
        String userId = jwtUtil.getUserIdFromToken(token);
        request.setAttribute("userId", userId);

        return true;
    }
}