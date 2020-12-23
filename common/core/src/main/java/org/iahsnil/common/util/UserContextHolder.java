package org.iahsnil.common.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * 用户上下文
 */
public class UserContextHolder {

    private final ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     */
    public static UserContextHolder getInstance() {
        return SingletonHolder.sInstance;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonHolder {
        private static final UserContextHolder sInstance = new UserContextHolder();
    }

    /**
     * 用户上下文中放入信息
     */
    public void setContext(Map<String, String> map) {
        threadLocal.set(map);
    }

    /**
     * 获取上下文中的信息
     */
    public Map<String, String> getContext() {
        return threadLocal.get();
    }

    /**
     * 获取上下文中的用户名
     */
    public String getUsername() {
        return Optional.ofNullable(threadLocal.get()).orElse(Maps.newHashMap()).get("user_name");
    }

    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }

}
