package org.iahsnil.auth.authorization.service.provider;


import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public ResponseBean<Account> getUserByUniqueId(String uniqueId) {
        return ResponseBean.success(new Account());
    }

    @Override
    public ResponseBean<Set<Role>> queryRolesByUserId(String userId) {
        return ResponseBean.success(new HashSet<>());
    }
}
