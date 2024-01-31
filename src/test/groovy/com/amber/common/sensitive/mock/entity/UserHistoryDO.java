package com.amber.common.sensitive.mock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("USERINFO_HISTORY")
public class UserHistoryDO {

    @TableId(type = IdType.UUID, value = "rid")
    String id;

    String userId;

    String historyEvent;

}
