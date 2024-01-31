package com.amber.common.sensitive.mock.mapper

import com.amber.common.sensitive.mock.entity.RoleDO
import com.amber.common.sensitive.mock.entity.UserDO
import com.amber.common.sensitive.mock.entity.UserHistoryDO
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Many
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface UserDAO extends BaseMapper<UserDO>{
    @Select("""SELECT u.*
                 FROM userinfo u 
                WHERE rid = #{id}""")
    @Results([
            @Result(property = "id", column = "rid", id = true),
            @Result(property = "name", column = "user_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "idCard", column = "idCard"),
            @Result(property = "password", column = "password"),
            @Result(property = "histories", many = @Many(select = "getHistoriesByUserId"), column = "rid"),
    ])
    UserDO getUserWithHistoriesById(String id)

    @Select("""SELECT h.* 
                 FROM userinfo u 
            LEFT JOIN userinfo_history h ON u.rid = h.user_id 
                WHERE u.rid = #{id}""")
    List<UserHistoryDO> getHistoriesByUserId(@Param("id") String userId)


    @Select("""SELECT r.* 
                 FROM roleinfo r 
            LEFT JOIN user_role sr ON r.rid = sr.roleid 
                WHERE sr.userid = #{id}""")
    @Results([
            @Result(property = "id", column = "rid"),
            @Result(property = "name", column = "role_name"),
    ])
    List<RoleDO> getRolesByUserId(@Param("id") String userId)
}
