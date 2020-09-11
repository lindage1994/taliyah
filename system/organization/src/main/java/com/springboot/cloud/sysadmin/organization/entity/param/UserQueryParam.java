package com.springboot.cloud.sysadmin.organization.entity.param;

import com.springboot.cloud.sysadmin.organization.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.param.BaseParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam extends BaseParam<User> {
    private String name;
    private String mobile;
    private String username;
}
