package com.amber.common.sensitive.mock.service

import com.amber.common.sensitive.mock.entity.UserHistoryDO
import com.amber.common.sensitive.mock.mapper.UserHistoryDAO
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class UserHistoryService extends ServiceImpl<UserHistoryDAO, UserHistoryDO> {
}
