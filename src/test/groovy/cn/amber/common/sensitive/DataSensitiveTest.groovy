package cn.amber.common.sensitive

import cn.amber.common.sensitive.mapper.UserDAO
import cn.amber.common.sensitive.entity.Role
import cn.amber.common.sensitive.entity.User
import cn.amber.common.sensitive.service.impl.RoleServiceImpl
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql

import java.util.stream.Collectors

@Sql(value = '/cn/amber/common/sensitive/DataSensitiveTest.sql')
@SpringBootTest
class DataSensitiveTest {
    @Autowired
    UserDAO userDAO

    @Autowired
    RoleServiceImpl roleService

    @Autowired
    JdbcTemplate jdbcTemplate

    @Test
    void test() {
        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer.class) == 0

        // Create
        User user = new User()
        user.setName('user name')
        user.setPhone('12345678901')
        user.setIdCard('234098uzxcv')
        user.setPassword('123456')

        assert userDAO.insert(user) == 1
        assert user.getId() > ''
        assert user.getPhone() == '12345678901'
        assert user.getPassword() == 'e10adc3949ba59abbe56e057f20f883e'
        assert user.getIdCard() != '234098uzxcv'
        def sm4ValueLen = '9743b76689643f810bb72478a7fb59a6'.length()
        assert user.getIdCard().length() == sm4ValueLen

        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer) == 1
        assert jdbcTemplate.queryForObject('select phone from userinfo', String) == '12345678901'
        assert jdbcTemplate.queryForObject('select password from userinfo', String) == 'e10adc3949ba59abbe56e057f20f883e'
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String) != '234098uzxcv'
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String).length() == sm4ValueLen

        // Retrieve

        User retrievedUser = userDAO.selectById(user.getId())
        assert retrievedUser.getPhone() == '123****8901'
        assert retrievedUser.getIdCard() == '234098uzxcv'

        // Update
        retrievedUser.setPhone('01234567890')
        retrievedUser.setIdCard('210103')
        userDAO.updateById(retrievedUser)
        assert retrievedUser.getPhone() == '01234567890'
        assert retrievedUser.getIdCard() != '210103'
        assert retrievedUser.getIdCard().length() == sm4ValueLen

        assert jdbcTemplate.queryForObject('select phone from userinfo', String) == '01234567890'
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String) != '210103'

        retrievedUser = userDAO.selectById(user.getId())
        assert retrievedUser.getPhone() == '012****7890'
        assert retrievedUser.getIdCard() == '210103'
    }

    @Test
    void saveBatch() {
        assert jdbcTemplate.queryForObject('select count(*) from roleinfo', Integer) == 0

        Role roleDO1 = new Role()
        roleDO1.setName('role name 1')
        roleDO1.setPhone('18911352460')
        roleDO1.setIdCard('110101200007289104')

        Role roleDO2 = new Role()
        roleDO2.setName('role name 2')
        roleDO2.setPhone('13011876690')
        roleDO2.setIdCard('110101200007289104')

        Role roleDO3 = new Role()
        roleDO3.setName('role name 3')
        roleDO3.setPhone('15928132177')
        roleDO3.setIdCard('21010120000728600X')

        List<Role> roleDOS = new ArrayList<>()
        roleDOS.add(roleDO1)
        roleDOS.add(roleDO2)
        roleDOS.add(roleDO3)

        roleService.saveBatch(roleDOS)

        def sm4ValueLen = '9898d58a8d2f0b28645d3864a2fbefd18202bbd8d0da9d10fc5b9cc9b7685d87'.length()
        assert roleDOS[0].getIdCard().length() == sm4ValueLen
        assert jdbcTemplate.queryForList('select phone from roleinfo', String) == ['18911352460', '13011876690', '15928132177']
        assert jdbcTemplate.queryForList('select id_card from roleinfo', String) != ['110101200007289104', '110101200007289104', '21010120000728600X']
        assert jdbcTemplate.queryForList('select id_card from roleinfo', String)[0].length() == sm4ValueLen

        List<Role> roles = roleService.list()
        assert roles.size() == 3
        assert roles[0].getPhone() == '189****2460'
        assert roles[1].getPhone() == '130****6690'
        assert roles[2].getPhone() == '159****2177'
        assert roles[0].getIdCard() == '110101200007289104'
        assert roles[1].getIdCard() == '110101200007289104'
        assert roles[2].getIdCard() == '21010120000728600X'

        List<Role> filterList = roles.stream().filter({ role -> role.getIdCard() == "110101200007289104" }).collect(Collectors.toList());
        assert filterList.size() == 2

        List<Role> roleList = new ArrayList<Role>()
        for (Role roleDO : filterList) {
            roleDO.setPhone('13111112222')
            roleDO.setIdCard('210103')
            roleList.add(roleDO)
        }
        roleService.updateBatchById(roleList)

        assert jdbcTemplate.queryForObject('select count(*) from roleinfo', Integer) == 3
        assert jdbcTemplate.queryForList('select phone from roleinfo', String) == ['13111112222', '13111112222', '15928132177']
        assert jdbcTemplate.queryForList('select id_card from roleinfo', String) != ['210103', '210103', '21010120000728600X']

        QueryWrapper<Role> wrapper = new QueryWrapper()
        wrapper.eq('phone', '13111112222')
        roles = roleService.list(wrapper)
        assert roles.size() == 2
        assert roles[0].getPhone() == '131****2222'
        assert roles[1].getPhone() == '131****2222'
        assert roles[0].getIdCard() == '210103'
        assert roles[1].getIdCard() == '210103'
    }
}
