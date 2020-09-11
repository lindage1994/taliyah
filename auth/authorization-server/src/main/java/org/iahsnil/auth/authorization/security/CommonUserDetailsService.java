package org.iahsnil.auth.authorization.security;

import lombok.extern.slf4j.Slf4j;
import org.iahsnil.auth.authorization.entity.Account;
import org.iahsnil.auth.authorization.entity.Role;
import org.iahsnil.auth.authorization.service.impl.LoadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
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
                account.getAccountNonLocked(),
                this.obtainGrantedAuthorities(account));
    }


    protected Set<GrantedAuthority> obtainGrantedAuthorities(Account user) {
        Set<Role> roles = loadUserService.queryUserRolesByUserId(user.getId());
        log.info("user:{},roles:{}", user.getUsername(), roles);
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
    }
}
