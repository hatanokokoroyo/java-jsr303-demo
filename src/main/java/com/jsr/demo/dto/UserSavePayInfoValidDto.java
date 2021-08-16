package com.jsr.demo.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserSavePayInfoValidDto {
    @NotNull(message = "userId不能为空")
    private String userId;

    @Valid
    @NotEmpty(message = "必须输入支付信息")
    private List<UserPayInfoDto> payInfoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserPayInfoDto> getPayInfoList() {
        return payInfoList;
    }

    public void setPayInfoList(List<UserPayInfoDto> payInfoList) {
        this.payInfoList = payInfoList;
    }
}
