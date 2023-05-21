package com.trouvaille.aladdin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trouvaille.aladdin.entity.Sales;
import com.trouvaille.aladdin.service.EmployeeService;
import com.trouvaille.aladdin.service.SalesService;
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
    @Autowired
    private SalesService salesService;
    
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
    @Test
    void updateSaleById () {
        Sales sales = new Sales();
        sales.setStatus(5);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id" , 1660100224204447745L);
        boolean flag = this.salesService.update(sales , queryWrapper);
        log.info(String.valueOf(flag));
        
    }
    
    
}
