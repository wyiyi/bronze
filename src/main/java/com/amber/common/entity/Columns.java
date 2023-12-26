package com.amber.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "脱敏列字段名配置")
@TableName("COLUMNS")
public class Columns {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("path")
    @ApiModelProperty(value = "接口路径")
    private String path;

    @ApiModelProperty(value = "字段id")
    @TableField("field_id")
    private String field_id;

    @TableField("field_name")
    @ApiModelProperty(value = "字段名称")
    private String field_name;

    @TableField("permission")
    @ApiModelProperty(value = "菜单权限标志")
    private String permission;

    @TableField("resource_name")
    @ApiModelProperty(value = "资源名称")
    private String resource_name;

    @TableField("sensitive")
    @ApiModelProperty(value = "脱敏类型")
    private String sensitive;


}

