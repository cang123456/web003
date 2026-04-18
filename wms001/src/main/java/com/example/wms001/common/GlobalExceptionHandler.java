package com.example.wms001.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理业务异常（比如你抛的 "请先登录"）
     */
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", e.getMessage());
        return result;
    }

    /**
     * 处理空指针
     */
    @ExceptionHandler(NullPointerException.class)
    public Map<String, Object> handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "系统异常：空指针");
        return result;
    }

    /**
     * 所有其他异常兜底
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        log.error("全局异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "系统异常，请联系管理员");
        return result;
    }
}