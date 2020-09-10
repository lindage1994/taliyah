package org.iahsnil.auth.authorization.service;

import org.iahsnil.auth.authorization.entity.Account;

public interface ILoadUserService {

    Account getAccountByName(String userName);

}
