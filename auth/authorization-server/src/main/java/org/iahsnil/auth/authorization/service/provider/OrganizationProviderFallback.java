package org.iahsnil.auth.authorization.service.provider;


import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Account getUserByUniqueId(String uniqueId) {
        Account defaultAccount = new Account();
        defaultAccount.setName("system");
        defaultAccount.setUsername("system");
        defaultAccount.setPassword("123456");
        defaultAccount.setEnabled(true);
        defaultAccount.setAccountNonExpired(true);
        defaultAccount.setCredentialsNonExpired(true);
        defaultAccount.setAccountNonLocked(true);
        return defaultAccount;
    }

    @Override
    public Set<Role> queryRolesByUserId(String userId) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());

        return roles;
    }
}
