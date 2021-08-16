package com.jsr.demo.dao;

import com.jsr.demo.dto.UserNormalValidDto;
import com.jsr.demo.dto.UserSavePayInfoValidDto;
import com.jsr.demo.util.JsonUtil;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public void saveUser(UserNormalValidDto user) {
        System.out.println("保存User: " + JsonUtil.toJsonString(user));
    }

    public void updateUserPhoneById(UserNormalValidDto user) {
        System.out.println("更新Phone: " + JsonUtil.toJsonString(user));
    }

    public void updateUserNameById(UserNormalValidDto user) {
        System.out.println("更新Name: " + JsonUtil.toJsonString(user));
    }

    public void saveUserPayInfo(UserSavePayInfoValidDto userPayInfo) {
        System.out.println("保存用户支付信息: " + JsonUtil.toJsonString(userPayInfo));
    }
}
