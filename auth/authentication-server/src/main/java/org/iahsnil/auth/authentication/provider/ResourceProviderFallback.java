package org.iahsnil.auth.authentication.provider;

import lombok.extern.slf4j.Slf4j;
import org.iahsnil.auth.authentication.po.Resource;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public ResponseBean<Set<Resource>> resources() {
        log.error("认证服务启动时加载资源异常！未加载到资源");
        return ResponseBean.fail(new HashSet<>());
    }

    @Override
    public ResponseBean<Set<Resource>> resources(String username) {
        log.error("认证服务查询用户异常！查询用户资源为空！");
        return ResponseBean.success(new HashSet<>());
    }
}
