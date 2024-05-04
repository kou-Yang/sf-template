package com.sf.common.web.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ky
 * @description 主键id
 * @date 2024-04-22 17:33
 */
@Data
public class PrimaryKeyParam {

    @NotBlank
    private String id;
}
