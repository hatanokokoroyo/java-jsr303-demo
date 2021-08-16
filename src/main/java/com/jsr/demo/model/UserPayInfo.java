package com.jsr.demo.model;

public class UserPayInfo {
    private Long userPayInfoId;

    private Long userId;

    private String cardNo;

    private String cardType;

    public Long getUserPayInfoId() {
        return userPayInfoId;
    }

    public void setUserPayInfoId(Long userPayInfoId) {
        this.userPayInfoId = userPayInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
