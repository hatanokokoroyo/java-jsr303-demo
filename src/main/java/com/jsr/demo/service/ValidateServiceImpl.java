package com.jsr.demo.service;

import com.jsr.demo.model.ValidateServiceParam;
import com.jsr.demo.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

//@Validated
@Service
public class ValidateServiceImpl implements ValidateService {

//    @Validated(value = ValidateService.Test1.class)
//    @Validated(value = ValidateServiceParam.Test1.class)
    @Override
    public String test1(@Valid ValidateServiceParam param) {
        String jsonString = JsonUtil.toJsonString(param);
        System.out.println(jsonString);
        return jsonString;
    }

    @Override
    public String test2(ValidateServiceParam param) {
        String jsonString = JsonUtil.toJsonString(param);
        System.out.println(jsonString);
        return jsonString;
    }
}
