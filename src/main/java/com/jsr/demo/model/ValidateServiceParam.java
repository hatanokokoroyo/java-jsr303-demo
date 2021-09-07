package com.jsr.demo.model;

import com.jsr.demo.service.ValidateService;

import javax.validation.constraints.NotNull;

public class ValidateServiceParam {
//    @NotNull(groups = ValidateService.Test1.class, message = "name can not be null")
    @NotNull(groups = Test1.class, message = "name can not be null")
    private String name;

//    @NotNull(groups = ValidateService.Test2.class, message = "age can not be null")
    @NotNull(groups = Test2.class, message = "age can not be null")
    private Integer age;

    @NotNull(message = "sex can not be null")
//    @NotNull(message = "sex can not be null", groups = {ValidateService.Test1.class, ValidateService.Test2.class})
    @NotNull(message = "sex can not be null", groups = {Test1.class, Test2.class})
    private Integer sex;

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

    public interface Test1{}
    public interface Test2{}
}
