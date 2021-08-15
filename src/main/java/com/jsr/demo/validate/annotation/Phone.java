package com.jsr.demo.validate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 验证手机号, 空和正确的手机号都能通过校验
 * <p>
 * 自定义规则, from javax.validation.Constraint
 * Each constraint annotation must host the following attributes:
 * <ul>
 *     <li>{@code String message() default [...];} which should default to an error
 *     message key made of the fully-qualified class name of the constraint followed by
 *     {@code .message}. For example {@code "{com.acme.constraints.NotSafe.message}"}</li>
 *     <li>{@code Class<?>[] groups() default {};} for user to customize the targeted
 *     groups</li>
 *     <li>{@code Class<? extends Payload>[] payload() default {};} for
 *     extensibility purposes</li>
 * </ul>
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Repeatable(value = PhoneHints.class)
public @interface Phone {

    /**
     * 校验不通过时的错误信息
     */
    String message() default "手机号校验失败";

    /**
     * 分组用
     */
    Class<?>[] groups() default {};

    /**
     * 扩展用
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 自定义属性, 手机号正则检测规则
     */
    String regex() default "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 自定义属性, 是否允许手机号为null
     */
    boolean allowNull() default true;
}
