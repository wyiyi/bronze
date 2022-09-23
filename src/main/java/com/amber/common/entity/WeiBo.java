package com.amber.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "��ȡ΢����ز���")
public class WeiBo {
    private String screen_name;

    private String text;

    private String created_at;
}
