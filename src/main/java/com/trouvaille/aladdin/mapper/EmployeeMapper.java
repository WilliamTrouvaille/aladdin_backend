package com.trouvaille.aladdin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
