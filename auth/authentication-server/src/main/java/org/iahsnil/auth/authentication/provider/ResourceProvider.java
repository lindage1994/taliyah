package org.iahsnil.auth.authentication.provider;

import org.iahsnil.auth.authentication.po.Resource;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class)
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    ResponseBean<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    ResponseBean<Set<Resource>> resources(@PathVariable("username") String username);
}
