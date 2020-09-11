package com.springboot.cloud.sysadmin.organization.entity.param;

import com.springboot.cloud.sysadmin.organization.entity.po.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.param.BaseParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuQueryParam extends BaseParam<Menu> {
    private String name;
}
