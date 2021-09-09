package com.jsr.demo.service;

import com.jsr.demo.model.ValidateServiceParam;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ValidateService {
    @Validated(value = ValidateServiceParam.Test1.class)
    String test1(@Valid ValidateServiceParam param);

    String test2(ValidateServiceParam param);
}
