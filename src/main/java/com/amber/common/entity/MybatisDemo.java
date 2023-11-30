package com.amber.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "Mybatis相关信息")
public class MybatisDemo {

    @TableId
    private long id;

    private String name;

    private String method;
}
