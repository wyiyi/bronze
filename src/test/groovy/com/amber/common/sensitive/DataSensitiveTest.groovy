package com.amber.common.sensitive

import com.amber.common.base.BaseApplicationTests
import com.amber.common.sensitive.mock.entity.UserHistoryDO
import com.amber.common.sensitive.mock.entity.UserRoleDO
import com.amber.common.sensitive.mock.entity.RoleDO
import com.amber.common.sensitive.mock.entity.UserDO
import com.amber.common.sensitive.mock.mapper.RoleDAO
import com.amber.common.sensitive.mock.mapper.UserDAO
import com.amber.common.sensitive.mock.service.RoleService
import com.amber.common.sensitive.mock.service.UserHistoryService
import com.amber.common.sensitive.mock.service.UserRoleService
import com.amber.common.sensitive.mock.service.UserService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.commons.lang3.RandomStringUtils
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets

@Sql
@Transactional
class DataSensitiveTest extends BaseApplicationTests{

    @Autowired
    UserDAO userDAO
    @Autowired
    RoleDAO roleDAO
    @Autowired
    UserService userService
    @Autowired
    RoleService roleService
    @Autowired
    UserRoleService userRoleService
    @Autowired
    UserHistoryService userHistoryService

    @Autowired
    JdbcTemplate jdbcTemplate

    def static final MD5_LEN = 32
    def name = 'user name'
    def phone = '12345678901'
    def idCard = '234098uzxcv'
    def pwd = '123456'

    @Test
    void cruTest() {
        def user = testCreate()
        def retrievedUser = testRetrieve(user.getId())
        testUpdate(retrievedUser)
    }

    def testCreate() {
        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer) == 0

        UserDO user = new UserDO()
        user.setName(name)
        user.setPhone(phone)
        user.setIdCard(idCard)
        user.setPassword(pwd)

        assert userDAO.insert(user) == 1
        assert user.getId() > ''
        // abb handler
        assert user.getPhone() == phone
        // md5 handler
        assert user.getPassword().length() == MD5_LEN
        // sm4hex handler
        assert user.getIdCard() != idCard && user.getIdCard().length() == getSm4HexLen(idCard)

        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer) == 1
        assert jdbcTemplate.queryForObject('select phone from userinfo', String) == phone
        assert jdbcTemplate.queryForObject('select password from userinfo', String).length() == MD5_LEN
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String) != '234098uzxcv'
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String).length() == getSm4HexLen(idCard)

        return user
    }

    static int getSm4HexLen(String str) {
        // SM4算法的块大小为16字节
        int blockSize = 16
        str > '' ?
                ((int) (str.getBytes(StandardCharsets.UTF_8).length / blockSize) + 1) * blockSize * 2 :
                0
    }

    def testRetrieve(String userId) {
        UserDO retrievedUser = userDAO.selectById(userId)
        assert retrievedUser.getPhone() != phone
        assert retrievedUser.getPhone() == '123*****901'
        assert retrievedUser.getIdCard() == idCard
        return retrievedUser
    }

    def testUpdate(UserDO userToUpdate) {
        def newPhone = '01234567890'
        def newIdCard = '12345678901234567'

        userToUpdate.setPhone(newPhone)
        userToUpdate.setIdCard(newIdCard)
        userDAO.updateById(userToUpdate)

        assert userToUpdate.getPhone() == newPhone
        assert userToUpdate.getIdCard() != newIdCard
        assert userToUpdate.getIdCard().length() == getSm4HexLen(newIdCard)

        assert jdbcTemplate.queryForObject('select phone from userinfo', String) == newPhone
        assert jdbcTemplate.queryForObject('select id_card from userinfo', String) != newIdCard

        def retrievedUser = userDAO.selectById(userToUpdate.getId())
        assert retrievedUser.getPhone() == '012*****890'
        assert retrievedUser.getIdCard() == newIdCard
    }

    @Test
    void batchTest() {
        testBatchInsert()
        List<UserDO> retrievedUsers = testBatchRetrieve()
        testBatchUpdate(retrievedUsers)
    }

    def testBatchInsert() {
        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer) == 0

        List<UserDO> users = new ArrayList<>()
        3.times {
            UserDO user = new UserDO()
            user.setName("${name}${it+1}")
            user.setPhone("${phone}${it+1}")
            user.setPassword("${pwd}${it+1}")
            user.setIdCard("${idCard}${it+1}")
            users.add(user)
        }
        userService.saveBatch(users)

        assert jdbcTemplate.queryForObject('select count(*) from userinfo', Integer) == 3
        // sm4hex handler
        assert users.get(0).getIdCard() != idCard
        assert users.get(0).getIdCard().length() == getSm4HexLen(idCard)
        // abb handler
        assert jdbcTemplate.queryForList('select phone from userinfo', String) == ["${phone}1", "${phone}2", "${phone}3"]
        assert users.get(0).getPhone() == "${phone}1"
        // sm4hex handler
        def queriedIdCard = jdbcTemplate.queryForObject("select id_card from userinfo where user_name='${name}2'", String)
        assert queriedIdCard != "${idCard}2"
        assert queriedIdCard.length() == getSm4HexLen("${idCard}2")
    }

    def testBatchRetrieve() {
        List<UserDO> retrievedUsers = userService.list()
        assert retrievedUsers.size() == 3
        assert retrievedUsers[0].getPhone() == '1234****9011'
        assert retrievedUsers[1].getPhone() == '1234****9012'
        assert retrievedUsers[2].getPhone() == '1234****9013'
        assert retrievedUsers[0].getIdCard() == "${idCard}1"
        assert retrievedUsers[1].getIdCard() == "${idCard}2"
        assert retrievedUsers[2].getIdCard() == "${idCard}3"
        return retrievedUsers
    }

    def testBatchUpdate(List<UserDO> retrievedUsers) {
        List<UserDO> usersToUpdate = new ArrayList<UserDO>()
        for (UserDO user : retrievedUsers) {
            user.setPhone(phone.reverse())
            user.setIdCard(idCard.reverse())
            usersToUpdate.add(user)
        }
        userService.updateBatchById(usersToUpdate)

        assert jdbcTemplate.queryForObject("select count(*) from userinfo where phone = '${phone.reverse()}'", Integer) == 3
        assert jdbcTemplate.queryForObject("select id_card from userinfo where user_name='${name}3'", String).length() == getSm4HexLen("${idCard.reverse()}")

        QueryWrapper<UserDO> wrapper = new QueryWrapper()
        wrapper.eq('phone', phone.reverse())
        retrievedUsers = userService.list(wrapper)
        assert retrievedUsers.size() == 3
        assert retrievedUsers[0].getPhone() == '109*****321'
        assert retrievedUsers[1].getIdCard() == idCard.reverse()
    }

    @Test
    void oneToManyTest() {
        def event = RandomStringUtils.randomAlphabetic(10)
        def user = testCreate()

        def histories = []
        3.times {
            histories.add(UserHistoryDO.builder().userId(user.getId()).historyEvent("$event$it").build())
        }
        userHistoryService.saveBatch(histories)

        user = userDAO.selectById(user.getId())
        assert user.histories == null

        user = userDAO.getUserWithHistoriesById(user.getId())
        assert user.histories.size() == 3
        assert user.histories[0].historyEvent == "${event}0"

        def dataInDB = jdbcTemplate.queryForList("select history_event from userinfo_history where user_id='${user.getId()}'", String)
        assert dataInDB[1].length() == getSm4HexLen("${event}1")
    }

    @Test
    void ManyToManyTest() {
        assert jdbcTemplate.queryForObject('select count(*) from ROLEINFO', Integer) == 0
        assert jdbcTemplate.queryForObject('select count(*) from USERINFO', Integer) == 0

        testBatchInsert()
        List<UserDO> users = userService.list()
        assert users.size() == 3

        List<RoleDO> roles = new ArrayList<>()
        3.times {
            RoleDO role = new RoleDO()
            role.setName("${name}${it+1}")
            role.setDescription("$phone$pwd$idCard${it+1}")
            roles.add(role)
        }
        roleService.saveBatch(roles)
        roles = roleService.list()
        assert roles.size() == 3

        List<UserRoleDO> userRoleRelations = new ArrayList<>()
        for (i in 0..<users.size()) {
            for (j in i..<roles.size()) {
                UserRoleDO userRoleDO = new UserRoleDO()
                userRoleDO.setUserid(users.get(i).getId())
                userRoleDO.setRoleid(roles.get(j).getId())
                userRoleRelations.add(userRoleDO)
            }
        }
        userRoleService.saveBatch(userRoleRelations)
        userRoleRelations = userRoleService.list()
        assert userRoleRelations.size() == 6

        List<RoleDO> roleList = userDAO.getRolesByUserId(users[0].id)
        assert roleList.size() == 3
        assert roleList[0].getId() > ''
        assert roleList[1].getName() == 'use****me2'
        assert roleList[2].getDescription() == "$phone$pwd${idCard}3"
        def desc = jdbcTemplate.queryForObject("select description from roleinfo where rid='${roleList[2].getId()}'", String)
        assert desc.length() == getSm4HexLen("$phone$pwd${idCard}3")

        List<UserDO> userList = roleDAO.getUsersByRoleId(roles.get(1).getId())
        assert userList.size() == 2
        assert userList[0].getPhone() == '1234****9011'
        assert userList[1].getIdCard() == "${idCard}2"
    }

}
