package org.iahsnil.gateway.admin.service;


import org.iahsnil.gateway.admin.entity.vo.GatewayRouteVo;
import org.iahsnil.gateway.admin.entity.param.GatewayRouteQueryParam;
import org.iahsnil.gateway.admin.entity.po.GatewayRoute;

import java.util.List;

public interface IGatewayRouteService {
    /**
     * 获取网关路由
     */
    GatewayRoute get(String id);

    /**
     * 新增网关路由
     */
    boolean add(GatewayRoute gatewayRoute);

    /**
     * 查询网关路由
     */
    List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    /**
     * 更新网关路由信息
     */
    boolean update(GatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由
     */
    boolean delete(String id);

    /**
     * 重新加载网关路由配置到redis
     */
    boolean overload();
}
