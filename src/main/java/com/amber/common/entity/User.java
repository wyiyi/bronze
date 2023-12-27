package com.amber.common.entity;

import com.amber.common.annotation.FieldDesensitize;
import com.amber.common.annotation.FieldEncrypt;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

@Data
@EqualsAndHashCode
@ApiModel(description = "相关信息")
@TableName(value = "USER", autoResultMap = true)
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "phone")
    @FieldDesensitize
    @ApiModelProperty(value = "手机号")
    private String phone;

    @TableField("id_card")
    @ApiModelProperty(value = "身份证")
    private String id_card;

    @TableField("address")
    @FieldEncrypt
    @ApiModelProperty(value = "地址")
    private String address;

    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField("create_date")
    @ApiModelProperty(value = "出生日期")
    private Date create_date;

    @TableField("sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    @TableField("birth")
    @ApiModelProperty(value = "生日")
    private Date birth;
}
