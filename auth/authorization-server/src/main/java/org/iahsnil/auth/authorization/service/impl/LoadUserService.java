package org.iahsnil.auth.authorization.service.impl;

import lombok.AllArgsConstructor;
import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;
import org.iahsnil.auth.authorization.service.ILoadUserService;
import org.iahsnil.auth.authorization.service.provider.OrganizationProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class LoadUserService implements ILoadUserService {

    private final OrganizationProvider organizationProvider;

    @Override
    public Account getAccountByName(String userName) {
        return organizationProvider.getUserByUniqueId(userName).getData();
    }

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
