package com.trouvaille.aladdin;

import com.trouvaille.aladdin.entity.Employee;
import com.trouvaille.aladdin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class AladdinApplicationTests {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Test
    void contextLoads () {
    }
    
    @Test
    void testService () {
        List<Employee> employees = employeeService.list(null);
        System.out.println(employees);
    }
    
}
