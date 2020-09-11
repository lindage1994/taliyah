package org.iahsnil.auth.authorization.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.po.BasePo;

@Data
@EqualsAndHashCode(callSuper = false )
@NoArgsConstructor
public class Account extends BasePo {
    private String name;
    private String mobile;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
}
