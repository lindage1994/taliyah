package com.springboot.cloud.sysadmin.organization.entity.param;

import com.springboot.cloud.sysadmin.organization.entity.po.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.param.BaseParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionQueryParam extends BaseParam<Position> {
    private String name;
}
