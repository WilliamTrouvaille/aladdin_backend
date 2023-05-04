/**
 * @projectName: aladdin
 * @package: com.example.aladdin.config
 * @className: MyBatisConfig
 * @author: William_Trouvaille
 * @description: MyBatis(+)设置
 * @date: 2022/7/26 14:57
 * @version: 1.0
 */
package com.trouvaille.aladdin.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class MyBatisConfig {
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor () {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
