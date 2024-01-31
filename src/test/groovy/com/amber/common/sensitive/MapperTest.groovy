package com.amber.common.sensitive

import com.amber.common.base.BaseApplicationTests
import com.amber.common.sensitive.mock.entity.RoleDO
import com.amber.common.sensitive.mock.entity.UserDO
import com.amber.common.sensitive.mock.mapper.UserDAO
import com.amber.common.sensitive.mock.service.RoleService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql

@Sql("DataSensitiveTest.sql")
class MapperTest extends BaseApplicationTests{

    @Autowired
    UserDAO userDAO

    @Autowired
    RoleService roleService

    @Test
    void testService() {
        RoleDO roleDO = new RoleDO()
        roleDO.setName('role name 1')
        roleDO.setDescription('role info')
        roleService.saveOrUpdate(roleDO)
    }

    @Test
    void testDao() {
        UserDO user = new UserDO()
        user.setName('user name')
        user.setPhone('12345678901')
        user.setIdCard('234098uzxcv')
        user.setPassword('123456')
        assert userDAO.insert(user) == 1
    }
}
