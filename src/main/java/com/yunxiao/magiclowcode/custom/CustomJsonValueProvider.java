package com.yunxiao.magiclowcode.custom;

import com.yunxiao.spring.core.protocol.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.ssssssss.magicapi.core.context.RequestEntity;
import org.ssssssss.magicapi.core.interceptor.ResultProvider;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Component
public class CustomJsonValueProvider implements ResultProvider, InitializingBean {
    private final Environment env;
    private boolean isDev;

    public CustomJsonValueProvider(final Environment env) {
        this.env = env;
    }

    @Override
    public void afterPropertiesSet() {
        this.isDev = Arrays.asList(env.getActiveProfiles()).contains("dev");
    }

    /**
     * 定义返回结果，默认返回JsonBean
     */
    @Override
    public Object buildResult(RequestEntity requestEntity, int code, String message, Object data) {
        Result.Builder<Object> builder = Result.of(data).codeAble(Result.CodeEnum.SUCCESS);
        if (isDev) {
            builder.properties(Map.of("time", LocalDateTime.now()));
        }
        return builder.build();
    }

    @Override
    public Object buildException(RequestEntity requestEntity, Throwable throwable) {
        Result.Builder<Void> builder = Result.ofNull().codeAble(Result.CodeEnum.FAIL);
        if (isDev) {
            builder.properties(Map.of("time", LocalDateTime.now(), "errorMessage", throwable.getMessage()));
        }
        return builder.build();
    }
}