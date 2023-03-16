package com.trouvaille.aladdin;

import com.trouvaille.aladdin.entity.Employee;
import com.trouvaille.aladdin.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AladdinApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
    }

    @Test
    void testService() {
        List<Employee> employees = employeeService.list(null);
        System.out.println(employees);
    }

}
