package com.jsr.demo.controller;

import com.jsr.demo.dao.UserMapper;
import com.jsr.demo.dto.UserNormalValidDto;
import com.jsr.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 展示一下@Valid和@Validated的基本的校验功能
     * 配合全局异常捕获
     */
    @PostMapping("/save0")
    public void save0(@Validated(UserNormalValidDto.Save.class) @RequestBody UserNormalValidDto dto) {
        // 校验通过, 执行保存操作
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userMapper.saveUser(user);
    }

    /**
     * 展示一下使用BindingResult的校验捕获
     * 如果参数中存在BindingResult, 参数校验后, 结果会注入到bindingResult中, 不会抛出异常
     * <p>
     * 用于检测到参数错误后, 尝试在补救后继续向下执行的场景
     */
    @PostMapping("/save1")
    public void save1(@Validated(UserNormalValidDto.Save.class) @RequestBody UserNormalValidDto dto, BindingResult bindingResult) throws BindException {
        // 如果name或sex错误, 赋值默认值
        if (bindingResult.hasFieldErrors("name")) {
            dto.setName("unknown name");
        }
        if (bindingResult.hasFieldErrors("sex")) {
            dto.setSex(0);
        }
        // 如果age错误, 直接抛出异常
        if (bindingResult.hasFieldErrors("age")) {
            throw new BindException(bindingResult);
        }
        // 执行保存操作
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        userMapper.saveUser(user);
    }

    @PostMapping("/updateName")
    public void updateName(@Validated(UserNormalValidDto.UpdateName.class) @RequestBody UserNormalValidDto dto) {

    }

    @PostMapping("/updatePhone")
    public void updatePhone(@Validated(UserNormalValidDto.UpdatePhone.class) @RequestBody UserNormalValidDto dto) {

    }
}
