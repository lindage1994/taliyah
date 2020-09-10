package org.iahsnil.common.web.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.po.BasePo;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {
    private String id;
}
