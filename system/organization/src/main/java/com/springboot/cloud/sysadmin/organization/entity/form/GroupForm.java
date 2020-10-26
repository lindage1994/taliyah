package com.springboot.cloud.sysadmin.organization.entity.form;

import com.springboot.cloud.sysadmin.organization.entity.po.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.iahsnil.common.web.entity.form.BaseForm;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class GroupForm extends BaseForm<Group> {

    @NotBlank(message = "用户组父id不能为空")
    @ApiModelProperty(value = "用户组父id")
    private String parentId;

    @NotBlank(message = "用户组名称不能为空")
    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "用户组描述")
    private String description;
}
