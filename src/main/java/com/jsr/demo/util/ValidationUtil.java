package com.jsr.demo.util;

import com.jsr.demo.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtil {
    private final Validator validator;

    public ValidationUtil(Validator validator) {
        this.validator = validator;
    }

    public void validate(Object o, Class<?>... groups) throws BusinessException {
        Set<ConstraintViolation<Object>> result = validator.validate(o, groups);
        if (result.isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<Object> objectConstraintViolation : result) {
            String message = objectConstraintViolation.getMessage();
            builder.append(message).append(";");
        }
        throw new BusinessException("000001", builder.toString());
    }
}
