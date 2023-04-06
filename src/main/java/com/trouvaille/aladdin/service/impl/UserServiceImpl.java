/**
 * @param null:
 * @return null
 * @author willi
 * @description 用户服务类
 * @date 2023/04/06 22:19
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.User;
import com.trouvaille.aladdin.mapper.UserMapper;
import com.trouvaille.aladdin.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
