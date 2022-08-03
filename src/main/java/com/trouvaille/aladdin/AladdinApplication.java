package com.trouvaille.aladdin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@ServletComponentScan
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class AladdinApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AladdinApplication.class, args);
        AladdinApplication.log.info("项目启动成功!");

    }

}