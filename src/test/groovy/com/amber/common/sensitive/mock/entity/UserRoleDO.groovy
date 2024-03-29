package com.amber.common.sensitive.mock.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Data

@Data
@TableName('USER_ROLE')
class UserRoleDO {
    @TableId(type = IdType.UUID, value = "rid")
    String id;

    String userid

    String roleid


}
