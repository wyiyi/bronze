package cn.amber.common.sensitive.mapper

import com.amber.common.entity.User
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface UserDAO extends BaseMapper<User>{
}
