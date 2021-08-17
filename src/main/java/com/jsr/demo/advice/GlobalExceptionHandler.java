package com.jsr.demo.advice;

import com.jsr.demo.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        System.out.println("捕获到MethodArgumentNotValidException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            builder.append(error.getDefaultMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
        System.out.println("捕获到ConstraintViolationException");
        StringBuilder builder = new StringBuilder();
        // 获取所有的错误参数校验结果, 并将message进行拼接
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            builder.append(constraintViolation.getMessage()).append(";");
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException e) {
        System.out.println("捕获到业务异常");
        return ResponseEntity.badRequest().body(e.getDefaultMessage());
    }
}
