package org.iahsnil.auth.authorization.service;

import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;

import java.util.Set;

public interface ILoadUserService {

    Account getAccountByName(String userName);

    Set<Role> queryUserRolesByUserId(String userId);

}
