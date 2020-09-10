package org.iahsnil.auth.authorization.security;

import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.service.impl.LoadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CommonUserDetailsService implements UserDetailsService {

    @Autowired
    private LoadUserService loadUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Account account = loadUserService.getAccountByName(userName);

        return new User(account.getUsername(),
                account.getPassword(),
                account.getEnabled(),
                account.getAccountNonExpired(),
                account.getCredentialsNonExpired(),
                account.getCredentialsNonExpired(),
                this.obtainGrantedAuthorities(account));
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(Account user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return roles;
    }
}
