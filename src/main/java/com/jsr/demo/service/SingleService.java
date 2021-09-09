package com.jsr.demo.service;

import com.jsr.demo.model.ValidateServiceParam;
import com.jsr.demo.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>SingleService</p>
 * description:
 *
 * @author lizekun
 * Create time 2021/9/9 09:51
 */
@Validated
@Service
public class SingleService {
    @Validated(value = {ValidateServiceParam.Test1.class})
    public String test3(@Valid ValidateServiceParam param) {
        return JsonUtil.toJsonString(param);
    }
}
