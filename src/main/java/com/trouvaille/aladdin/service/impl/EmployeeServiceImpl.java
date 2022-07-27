/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: EmployeeServiceImpl
 * @author: William_Trouvaille
 * @description: 员工ServiceImpl
 * @date: 2022/7/26 19:58
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Employee;
import com.trouvaille.aladdin.mapper.EmployeeMapper;
import com.trouvaille.aladdin.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
