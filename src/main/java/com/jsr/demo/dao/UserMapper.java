package com.jsr.demo.dao;

import com.jsr.demo.model.User;
import com.jsr.demo.util.JsonUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public void saveUser(User user) {
        System.out.println("保存User: " + JsonUtil.toJsonString(user));
    }
}
