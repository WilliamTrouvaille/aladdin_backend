/**
 * @projectName: reggieTakeOut
 * @package: com.trouvaille.reggietakeout.controller
 * @className: UserController
 * @author: Eric
 * @description: TODO
 * @date: 2022/7/22 16:15
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.User;
import com.trouvaille.aladdin.service.UserService;
import com.trouvaille.aladdin.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param user:
     * @param session:
     * @return R<String>
     * @author William_Trouvaille
     * @description 发送短信
     * @date 2022/07/22 16:47
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {

            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            log.info("sendMsg---code==>{}", code);

//            SmsUtils.singleSend(phone, code);


            redisTemplate.opsForValue().set(phone, code, 10, TimeUnit.MINUTES);

            return R.success("验证码短信发送成功!");
        }

        return R.error("验证码短信发送失败,请重试!");
    }


    /**
     * @param map:
     * @param session:
     * @return R<User>
     * @author William_Trouvaille
     * @description 移动端用户登录
     * @date 2022/07/22 16:49
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();

        //从Session中获取保存的验证码
//        Object codeInSession = session.getAttribute(phone);

        Object codeInSession = redisTemplate.opsForValue().get(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInSession != null && codeInSession.equals(code)) {
            //如果能够比对成功，说明登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(queryWrapper);
            if (user == null) {
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                user.setName("用户" + phone.substring(7));
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            redisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登录失败");
    }

    /**
     * @param page:
     * @param pageSize:
     * @param name:
     * @return R<Page < User>>
     * @author willi
     * @description 分页获取用户信息
     * @date 2023/04/20 22:55
     */
    @GetMapping("/page")
    public R<Page<User>> page(int page, int pageSize, String name) {
        log.info("User-page:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<User> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, User::getName, name);
        lqw.orderByDesc(User::getCreateTime);
        userService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }

    /**
     * @param id:
     * @param status:
     * @return R<String>
     * @author willi
     * @description 更改用户状态
     * @date 2023/04/22 15:56
     */
    @PutMapping("/status")
    public R<String> status(Long id, int status) {
        log.info("更改用户状态:ids==>{},status==>{}", id, status);
        User user = userService.getById(id);
        user.setStatus(status);
        boolean flag = userService.updateById(user);
        return flag ? R.success("用户状态已经更改成功！") : R.error("用户状态更改失败,请重试!");
    }

}
