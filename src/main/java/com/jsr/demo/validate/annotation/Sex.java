package com.jsr.demo.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Min(0)
@Max(1)
public @interface Sex {
    /**
     * 校验不通过时的错误信息
     */
    String message() default "性别必须是0或1";

    /**
     * 分组用
     */
    Class<?>[] groups() default {};

    /**
     * 扩展用
     */
    Class<? extends Payload>[] payload() default {};
}
