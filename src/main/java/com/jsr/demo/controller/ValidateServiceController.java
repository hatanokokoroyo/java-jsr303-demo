package com.jsr.demo.controller;

import com.jsr.demo.model.ValidateServiceParam;
import com.jsr.demo.service.SingleService;
import com.jsr.demo.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validateService")
public class ValidateServiceController {
    @Autowired
    private ValidateService validateService;
    @Autowired
    private SingleService singleService;

    public ValidateServiceController(ValidateService validateService) {
        this.validateService = validateService;
    }

    @PostMapping("/test1")
    public String test1(@RequestBody ValidateServiceParam param) {
        return validateService.test1(param);
    }

    @PostMapping("/test3")
    public String test3(@RequestBody ValidateServiceParam param) {
        return singleService.test3(param);
    }
}
