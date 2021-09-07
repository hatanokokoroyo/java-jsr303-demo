package com.jsr.demo.service;

import com.jsr.demo.dto.UserNormalValidDto;
import com.jsr.demo.dto.UserQuery;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.Valid;

/**
 * <p>UserInfoService</p>
 * description:
 *
 * @author lizekun
 * Create time 2021/8/19 09:32
 */
@Validated
public interface UserInfoService {
    void testService(UserQuery dto);

    @GroupSequence(UserNormalValidDto.Save.class)
    @interface TestService2{}
    void testService2(@Valid UserNormalValidDto dto);
}
