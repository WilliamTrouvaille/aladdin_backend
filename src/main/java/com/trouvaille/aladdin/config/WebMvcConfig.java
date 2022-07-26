/**
 * @projectName: aladdin
 * @package: com.example.aladdin.config
 * @className: WebMvcConfig
 * @author: Eric
 * @description: TODO
 * @date: 2022/7/26 15:02
 * @version: 1.0
 */
package com.trouvaille.aladdin.config;

import com.trouvaille.aladdin.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息对象转换器
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器,底层使用Jackson将java对象转换成JSON
        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器利象追加到MVC框架前转换器缤合中
        converters.add(0, mappingJackson2HttpMessageConverter);
    }
}