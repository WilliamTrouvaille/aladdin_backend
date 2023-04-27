/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.common
 * @className: MyMetaObjectHandle
 * @author: William_Trouvaille
 * @description: 自动填充相关
 * @date: 2022/7/26 15:08
 * @version: 1.0
 */
package com.trouvaille.aladdin.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long id = BaseContext.getCurrentId();
        log.info("当前创建员工id为==>{}", id);

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", id);
        metaObject.setValue("updateUser", id);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long id = BaseContext.getCurrentId();

        log.info("当前创建员工id为==>{}", id);
        log.info("当前更改员工id为==>{}", id);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", id);
    }
}
