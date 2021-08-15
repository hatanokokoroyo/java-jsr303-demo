package com.jsr.demo.dto;

import com.jsr.demo.validate.annotation.Phone;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class UserNormalValidDto {
    @NotBlank(message = "userId不能为空", groups = {UpdateName.class, UpdatePhone.class})
    private String userId;
    /**
     * 不可为空且长度必须在5-20
     */
    @NotNull(message = "name不能为空", groups = {Save.class, UpdateName.class})
    @Length(min = 5, max = 20, message = "名称长度必须在5-20", groups = {Save.class})
    private String name;

    /**
     * 不可为空且必须大于1
     */
    @NotNull(message = "age不能为空", groups = {Save.class})
    @Min(value = 1, message = "age必须大于等于1", groups = {Save.class})
    private Integer age;

    /**
     * 可以为空, 非空情况下必须是0或1
     * 可以多个注解组合成一个自定义注解(无自定义校验器)
     */
    @Min(value = 0, message = "sex必须是0或1")
    @Max(value = 1, message = "sex必须是0或1")
//    @Sex(groups = {Save.class})
    private Integer sex;

    /**
     * 可以为空, 非空情况下必须是合法的邮箱地址
     * 默认校验器 org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator
     * 如果有需求, 可以自己填写正则规则 @Email(message = "请输入合法的邮箱地址", regexp = ".*")
     */
    @Email(message = "请输入合法的邮箱地址", groups = {Save.class})
    private String email;

    /**
     * 自定义的手机号注解(含自定义校验器)
     * 可以为空, 非空情况下必须是合法的手机号
     * 如果部分参数需要根据不同的group使用不同的参数, 可以定义Hints
     */
    @Phone(message = "请输入正确的手机号", groups = {Save.class, UpdatePhone.class})
//    @Phone(message = "请输入正确的手机号", groups = {Save.class})
//    @Phone(message = "请输入正确的手机号", allowNull = false, groups = {UpdatePhone.class})
    private String phone;

    public interface Save {
    }

    public interface UpdateName {
    }

    public interface UpdatePhone {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
