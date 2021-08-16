package com.jsr.demo.controller;

import com.jsr.demo.dto.PageParam;
import com.jsr.demo.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/user1")
@Validated
public class UserAllValidatedController {
    /**
     * 抛出ConstraintViolationException
     */
    @GetMapping("/getUser2/{sex}")
    public PageParam<User> getUser2(@PathVariable @Min(0) @Max(1) Integer sex) {
        return new PageParam<>();
    }

    /**
     * 抛出ConstraintViolationException
     */
    @GetMapping("/getUser3")
    public PageParam<User> getUser3(@RequestParam @Min(0) @Max(1) Integer sex) {
        return new PageParam<>();
    }
}
