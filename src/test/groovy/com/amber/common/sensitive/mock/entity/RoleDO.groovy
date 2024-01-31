package com.amber.common.sensitive.mock.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data

@Data
@TableName('ROLEINFO')
class RoleDO {
    @TableId(type = IdType.UUID, value='rid')
    String id

    @TableField('role_name')
    String name

    String description

    @TableField(exist = false)
    List<UserDO> users;
}
