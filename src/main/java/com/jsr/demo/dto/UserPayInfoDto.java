package com.jsr.demo.dto;

import javax.validation.constraints.NotBlank;

public class UserPayInfoDto {
    @NotBlank(message = "cardNo不能为空")
    private String cardNo;
    @NotBlank(message = "cardType不能为空")
    private String cardType;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
