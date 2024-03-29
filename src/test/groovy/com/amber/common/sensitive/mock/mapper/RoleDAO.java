package com.amber.common.sensitive.mock.mapper;

import com.amber.common.sensitive.mock.entity.RoleDO;
import com.amber.common.sensitive.mock.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDAO extends BaseMapper<RoleDO> {
    @Select("SELECT u.* FROM userinfo u LEFT JOIN user_role sr ON u.rid = sr.userid WHERE sr.roleid = #{id}")
    @Results({
            @Result(property = "id", column = "rid"),
            @Result(property = "name", column = "user_name")
    })
    List<UserDO> getUsersByRoleId(@Param("id") String roleid);
}
