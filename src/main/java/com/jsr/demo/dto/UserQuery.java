package com.jsr.demo.dto;

import javax.validation.constraints.NotBlank;

public class UserQuery {
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
}
