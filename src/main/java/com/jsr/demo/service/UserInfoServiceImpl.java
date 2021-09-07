package com.jsr.demo.service;

import com.jsr.demo.dto.UserNormalValidDto;
import com.jsr.demo.dto.UserQuery;
import com.jsr.demo.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>UserInfoServiceImpl</p>
 * description:
 *
 * @author lizekun
 * Create time 2021/8/19 09:32
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public void testService(@Valid UserQuery dto) {
        System.out.println(JsonUtil.toJsonString(dto));
    }

    @Validated
    @Override
    public void testService2(@Valid UserNormalValidDto dto) {
        System.out.println(JsonUtil.toJsonString(dto));
    }
}
