package com.jsr.demo.dto;

import com.jsr.demo.validate.annotation.Phone;

import javax.validation.constraints.NotBlank;

public class UserQuery {
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
