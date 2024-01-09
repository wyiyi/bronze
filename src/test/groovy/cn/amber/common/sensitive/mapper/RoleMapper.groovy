package cn.amber.common.sensitive.mapper

import cn.amber.common.sensitive.entity.Role
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface RoleMapper extends BaseMapper<Role>{

}
