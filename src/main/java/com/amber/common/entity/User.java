package com.amber.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "微博评论相关信息")
public class User {
    private String screen_name;

    private String text;

    private String created_at;
}
