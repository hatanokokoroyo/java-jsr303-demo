package com.jsr.demo.validate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private String regex;
    private boolean allowNull;

    @Override
    public void initialize(Phone phone) {
        regex = phone.regex();
        allowNull = phone.allowNull();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return allowNull;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
