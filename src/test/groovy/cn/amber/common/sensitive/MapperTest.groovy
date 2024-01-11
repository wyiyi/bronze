package cn.amber.common.sensitive

import cn.amber.common.sensitive.entity.RoleDO
import cn.amber.common.sensitive.entity.UserDO
import cn.amber.common.sensitive.mapper.UserDAO
import cn.amber.common.sensitive.service.impl.RoleServiceImpl
import com.amber.common.util.CodeUtil
import com.amber.common.util.DeEncode
import com.amber.common.util.WaterMark
import io.github.springroll.test.AbstractSpringTest
import org.apache.ibatis.jdbc.SQL
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(classes = MapperTest.class)
@Sql
class MapperTest extends AbstractSpringTest{

    @Autowired
    DeEncode deEncode

//    @Autowired
//    UserDAO userDAO
//
//    @Autowired
//    RoleServiceImpl roleService

    @Test
    void testAutoWired() {
        String watermarkInput = "抓鸭子，抓几只？";
        deEncode.encode(watermarkInput);
    }

//    @Test
//    void testService() {
//        RoleDO roleDO = new RoleDO()
//        roleDO.setName('role name 1')
//        roleDO.setPhone('18911352460')
//        roleDO.setIdCard('110101200007289104')
//        roleService.saveOrUpdate(roleDO)
//    }
//
//    @Test
//    void testDao() {
//        UserDO user = new UserDO()
//        user.setName('user name')
//        user.setPhone('12345678901')
//        user.setIdCard('234098uzxcv')
//        user.setPassword('123456')
//        assert userDAO.insert(user) == 1
//    }
}
