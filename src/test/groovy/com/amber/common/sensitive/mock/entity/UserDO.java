package com.amber.common.sensitive.mock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("USERINFO")
public class UserDO {

    @TableId(type = IdType.UUID, value = "rid")
    String id;

    @TableField("user_name")
    String name;

    String phone;

    String idCard;

    String password;

    @TableField(exist = false)
    List<UserHistoryDO> histories;

    @TableField(exist = false)
    List<RoleDO> roles;

}
