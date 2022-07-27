/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: EmployeeController
 * @author: William_Trouvaille
 * @description: 员工Controller
 * @date: 2022/7/26 20:11
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * @param employee:
     * @param request:
     * @return R<Employee>
     * @author William_Trouvaille
     * @description 登录
     * @date 2022/07/26 20:24
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {


//        ` 将页面提交的密码password进行md5加密处理
        String pwd = employee.getPassword();
        String password = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));

        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getUsername, employee.getUsername());
        Employee employeeOne = employeeService.getOne(lqw);

        if (employeeOne == null || !employeeOne.getPassword().equals(password)) {
            return R.error("账号密码输入错误,登陆失败!");
        }
        if (employeeOne.getStatus() == 0) {
            return R.error("账号已禁用,登陆失败!");
        }

        //        登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", employeeOne.getId());
        return R.success(employeeOne);
    }


    /**
     * @param request:
     * @return R<String>
     * @author William_Trouvaille
     * @description 员工登出
     * @date 2022/07/26 20:35
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //        清理当前session保存的数据
        request.getSession().removeAttribute("employee");
        return R.success("登出成功");
    }

    /**
     * @param page:
     * @param pageSize:
     * @param name:
     * @return R<Page < Employee>>
     * @author William_Trouvaille
     * @description 分页查询
     * @date 2022/07/26 20:40
     */
    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {
        log.info("Employee:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Employee::getName, name);
        lqw.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }


    /**
     * @param employee:
     * @param request:
     * @return R<String>
     * @author William_Trouvaille
     * @description 新增员工
     * @date 2022/07/27 14:14
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request) {
        log.info("新增员工,员工信息:{}", employee);

//设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        boolean save = employeeService.save(employee);

        if (save) {
            return R.success("添加员工成功");
        } else {
            return R.error("添加员工失败!请重试!");
        }
    }

    /**
     * @param id:
     * @return R<Employee>
     * @author William_Trouvaille
     * @description 更该员工-回显-根据id查询
     * @date 2022/07/27 14:14
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }

    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request) {
        log.info("更改员工信息,员工信息==>{}", employee.toString());
        Long empID = (Long) request.getSession().getAttribute("employee");

        employee.setUpdateUser(empID);
        employee.setUpdateTime(LocalDateTime.now());

        boolean flag = employeeService.updateById(employee);

        return R.flag(flag, "员工信息更改成功!", "员工信息更改失败,请重试!");


    }

}
