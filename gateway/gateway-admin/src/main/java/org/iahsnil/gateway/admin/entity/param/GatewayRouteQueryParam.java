package org.iahsnil.gateway.admin.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.param.BaseParam;
import org.iahsnil.gateway.admin.entity.po.GatewayRoute;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute> {
    private String uri;
}
