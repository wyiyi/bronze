package com.amber.common.sensitive.mock.service;

import com.amber.common.sensitive.mock.entity.RoleDO;
import com.amber.common.sensitive.mock.mapper.RoleDAO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceImpl<RoleDAO, RoleDO> {
}
