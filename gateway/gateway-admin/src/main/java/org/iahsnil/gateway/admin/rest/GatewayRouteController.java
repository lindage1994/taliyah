package org.iahsnil.gateway.admin.rest;


import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.response.ResponseBean;
import org.iahsnil.gateway.admin.entity.form.GatewayRouteForm;
import org.iahsnil.gateway.admin.entity.form.GatewayRouteQueryForm;
import org.iahsnil.gateway.admin.entity.vo.GatewayRouteVo;
import org.iahsnil.gateway.admin.entity.param.GatewayRouteQueryParam;
import org.iahsnil.gateway.admin.entity.po.GatewayRoute;
import org.iahsnil.gateway.admin.service.IGatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {

    @Autowired
    private IGatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public ResponseBean<Boolean> add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.info("name:{}", gatewayRoutForm);
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return ResponseBean.success(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public ResponseBean<Boolean> delete(@PathVariable String id) {
        return ResponseBean.success(gatewayRoutService.delete(id));
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public ResponseBean<Boolean> update(@PathVariable String id, @Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        gatewayRout.setId(id);
        return ResponseBean.success(gatewayRoutService.update(gatewayRout));
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public ResponseBean<GatewayRouteVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        return ResponseBean.success(new GatewayRouteVo(gatewayRoutService.get(id)));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @GetMapping
    public ResponseBean<Optional<GatewayRouteVo>> getByUri(@RequestParam String uri) {
        return ResponseBean.success(gatewayRoutService.query(new GatewayRouteQueryParam(uri)).stream().findFirst());
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRoutQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @PostMapping(value = "/conditions")
    public ResponseBean<List<GatewayRouteVo>> search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        return ResponseBean.success(gatewayRoutService.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class)));
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @PostMapping(value = "/overload")
    public ResponseBean<Boolean> overload() {
        return ResponseBean.success(gatewayRoutService.overload());
    }

}