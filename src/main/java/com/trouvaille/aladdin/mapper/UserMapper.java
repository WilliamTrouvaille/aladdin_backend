/**
 * @param null:
 * @return null
 * @author willi
 * @description 用户
 * @date 2023/04/06 22:18
 */
package com.trouvaille.aladdin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
