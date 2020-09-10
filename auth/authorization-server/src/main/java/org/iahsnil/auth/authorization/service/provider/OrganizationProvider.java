package org.iahsnil.auth.authorization.service.provider;


import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user")
    Account getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    Set<Role> queryRolesByUserId(@PathVariable("userId") String userId);
}
