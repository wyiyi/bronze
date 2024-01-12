package com.amber.common.sensitive

import com.amber.common.base.BaseApplicationTests
import com.amber.common.sensitive.entity.RoleDO
import com.amber.common.sensitive.entity.UserDO
import com.amber.common.sensitive.mapper.UserDAO
import com.amber.common.sensitive.service.impl.RoleServiceImpl
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql

@Sql("DataSensitiveTest.sql")
class MapperTest extends BaseApplicationTests{

    @Autowired
    UserDAO userDAO

    @Autowired
    RoleServiceImpl roleService

    @Test
    void testService() {
        RoleDO roleDO = new RoleDO()
        roleDO.setName('role name 1')
        roleDO.setPhone('18911352460')
        roleDO.setIdCard('110101200007289104')
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
