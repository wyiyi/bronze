package com.amber.common.sensitive.mock.service;

import com.amber.common.sensitive.mock.entity.UserDO;
import com.amber.common.sensitive.mock.mapper.UserDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDAO, UserDO> {
}
