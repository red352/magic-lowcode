package com.yunxiao.magiclowcode.custom;

import org.ssssssss.magicapi.core.context.MagicUser;
import org.ssssssss.magicapi.core.exception.MagicLoginException;
import org.ssssssss.magicapi.core.interceptor.AuthorizationInterceptor;

/**
 * 自定义用户名密码登录
 */
//@Component  //注入到Spring容器中
public class CustomAuthorizationInterceptor implements AuthorizationInterceptor {

    /**
     * 配置是否需要登录
     */
    @Override
    public boolean requireLogin() {
        return true;
    }

    /**
     * 根据Token获取User
     */
    @Override
    public MagicUser getUserByToken(String token) throws MagicLoginException {
        if (token != null) {
            return new MagicUser("1", "admin", "1");   // 从token中获取MagicUser对象
        }
        throw new MagicLoginException("token无效");
    }

    @Override
    public MagicUser login(String username, String password) throws MagicLoginException {
        // 根据实际情况进行修改，如查询数据库。。
        if ("admin".equals(username) && "admin".equals(password)) {
            // 登录成功后 构造MagicUser对象。
            return new MagicUser("1", "admin", "1");
        }
        throw new MagicLoginException("用户名或密码不正确");
    }

}