package org.iahsnil.gateway.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.iahsnil.gateway.admin.entity.po.GatewayRoute;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}