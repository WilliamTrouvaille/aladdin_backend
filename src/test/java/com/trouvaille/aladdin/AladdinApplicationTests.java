package com.trouvaille.aladdin;

import com.trouvaille.aladdin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class AladdinApplicationTests {
    
    @Autowired
    private EmployeeService employeeService;
    
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    void contextLoads () {
    }

//    @Test
//    void testService () {
//        List<Employee> employees = this.employeeService.list(null);
//        System.out.println(employees);
//    }
//

//    @Test
//    void testRedisKey () {
//        String redisKey = "Test:test:1";
//        if (this.redisTemplate.hasKey(redisKey)) {
//            log.info((String) this.redisTemplate.opsForValue().get(redisKey));
//        }
//        this.redisTemplate.opsForValue().set(redisKey , "test" , 60L , TimeUnit.SECONDS);
//
//    }
//
//    @Test
//    void deleteRedisKey () {
//        String redisKey = "Test*";
//        if (this.redisTemplate.hasKey(redisKey)) {
//            this.redisTemplate.delete(redisKey);
//        }
//    }
    
}
