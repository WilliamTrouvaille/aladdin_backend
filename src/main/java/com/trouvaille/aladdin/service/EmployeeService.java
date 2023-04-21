package com.trouvaille.aladdin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trouvaille.aladdin.entity.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    boolean updateStatus(List<Long> ids, int status);
}
