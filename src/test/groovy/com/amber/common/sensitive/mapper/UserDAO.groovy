package com.amber.common.sensitive.mapper

import com.amber.common.sensitive.entity.UserDO
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface UserDAO extends BaseMapper<UserDO>{
}
