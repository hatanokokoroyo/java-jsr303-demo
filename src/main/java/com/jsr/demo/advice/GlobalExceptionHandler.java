package com.jsr.demo.advice;

import com.jsr.demo.util.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(BindException e) {
        System.out.println("捕获到BindException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            builder.append(error.getDefaultMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }
}
