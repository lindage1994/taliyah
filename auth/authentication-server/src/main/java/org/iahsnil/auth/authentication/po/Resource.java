package org.iahsnil.auth.authentication.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.iahsnil.common.web.entity.po.BasePo;

@Data
@NoArgsConstructor
public class Resource extends BasePo {
    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
}
