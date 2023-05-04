package com.trouvaille.aladdin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trouvaille.aladdin.entity.Employee;

public interface EmployeeService extends IService<Employee> {
    /**
     * @param id:
     * @param status:
     * @return boolean
     * @description: 修改员工状态
     * @date: 2022/7/26 20:00
     */
    boolean updateStatus (Long id , int status);
}
