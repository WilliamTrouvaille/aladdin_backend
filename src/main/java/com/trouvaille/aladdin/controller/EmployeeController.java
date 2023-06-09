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
import com.trouvaille.aladdin.entity.Employee;
import com.trouvaille.aladdin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    
    /**
     * @param employee:
     * @param request:
     * @return R  <  Employee>
     * @author William_Trouvaille
     * @description 登录
     * @date 2022/07/26 20:24
     */
    @PostMapping ("/login")
    public R<Employee> login (@RequestBody Employee employee , HttpServletRequest request) {


//        ` 将页面提交的密码password进行md5加密处理
        String pwd = employee.getPassword();
        String password = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getUsername , employee.getUsername());
        Employee employeeOne = this.employeeService.getOne(lqw);
        
        if (employeeOne == null || ! employeeOne.getPassword().equals(password)) {
            return R.error("账号密码输入错误,登陆失败!");
        }
        if (employeeOne.getStatus() == 0) {
            return R.error("账号已禁用,登陆失败!");
        }
        Set<String> redisKey = this.redisTemplate.keys("*");
        this.redisTemplate.delete(redisKey);
        
        //        登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee" , employeeOne.getId());
        return R.success(employeeOne);
    }
    
    
    /**
     * @param request:
     * @return R  <  String>
     * @author William_Trouvaille
     * @description 员工登出
     * @date 2022/07/26 20:35
     */
    @PostMapping ("/logout")
    public R<String> logout (HttpServletRequest request) {
        //        清理当前session保存的数据
        request.getSession().removeAttribute("employee");
        Set<String> redisKey = this.redisTemplate.keys("*");
        this.redisTemplate.delete(redisKey);
        return R.success("登出成功");
    }
    
    /**
     * @param page:
     * @param pageSize:
     * @param name:
     * @return R < Page  <  Employee>>
     * @author William_Trouvaille
     * @description 分页查询
     * @date 2022/07/26 20:40
     */
    @GetMapping ("/page")
    public R<Page<Employee>> page (int page , int pageSize , String name) {
        log.info("Employee:name, page, pageSize==>{},{},{}" , name , page , pageSize);
        
        String redisKey = "Employee:page:" + page + ":" + pageSize + ":" + name;
        if (this.redisTemplate.hasKey(redisKey)) {
            return R.success((Page<Employee>) this.redisTemplate.opsForValue().get(redisKey));
        }
        
        
        Page<Employee> pageInfo = new Page<>(page , pageSize);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null , Employee::getName , name);
        lqw.orderByDesc(Employee::getUpdateTime);
        this.employeeService.page(pageInfo , lqw);
        
        
        this.redisTemplate.opsForValue().set(redisKey , pageInfo , 60L , TimeUnit.MINUTES);
        
        return R.success(pageInfo);
        
    }
    
    
    /**
     * @param employee:
     * @return R  <  String>
     * @author William_Trouvaille
     * @description 新增员工
     * @date 2022/07/27 14:14
     */
    @PostMapping
    public R<String> save (@RequestBody Employee employee) {
        log.info("新增员工,员工信息:{}" , employee);


//设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        boolean save = this.employeeService.save(employee);
        
        if (save) {
//            String redisKey = "Employee*";
            Set<String> redisKey = this.redisTemplate.keys("Employee" + "*");
            this.redisTemplate.delete(redisKey);
            return R.success("添加员工成功");
        } else {
            return R.error("添加员工失败!请重试!");
        }
    }
    
    /**
     * @param id:
     * @return R < Employee>
     * @author William_Trouvaille
     * @description 更该员工-回显-根据id查询
     * @date 2022/07/27 14:14
     */
    @GetMapping ("/{id}")
    public R<Employee> getById (@PathVariable Long id) {
        Employee employee = this.employeeService.getById(id);
        return R.success(employee);
    }
    
    /**
     * @param employee:
     * @return R < String>
     * @author willi
     * @description 修改员工信息
     * @date 2023/04/21 18:44
     */
    @PutMapping
    public R<String> update (@RequestBody Employee employee) {
        log.info("更改员工信息,员工信息==>{}" , employee.toString());
        
        boolean flag = this.employeeService.updateById(employee);
        Set<String> redisKey = this.redisTemplate.keys("Employee" + "*");
        this.redisTemplate.delete(redisKey);
        
        
        return R.flag(true , "员工信息更改成功!" , "员工信息更改失败,请重试!");
    }
    
    /**
     * @param id:
     * @param status:
     * @return R < String>
     * @author willi
     * @description 更改员工状态
     * @date 2023/04/22 15:56
     */
    @PutMapping ("/status")
    public R<String> status (Long id , int status) {
        log.info("更改员工状态:ids==>{},status==>{}" , id , status);
        boolean flag = this.employeeService.updateStatus(id , status);
        
        
        Set<String> redisKey = this.redisTemplate.keys("Employee" + "*");
        this.redisTemplate.delete(redisKey);
        return flag ? R.success("员工状态已经更改成功！") : R.error("员工状态更改失败,请重试!");
        
    }
    
    
    /**
     * @param id:
     * @param oldPwd:
     * @param newPwd:
     * @return R  <  String>
     * @author willi
     * @description 更改密码
     * @since 2023/05/04 15:35
     */
    @GetMapping ("/updatePwd")
    public R<String> updatePwd (String employeeId , String oldPwd , String newPwd) {
        
        log.info("员工更改密码:oldPwd==>{},newPwd==>{},employeeId==>{}" , oldPwd , newPwd , employeeId);
        
        
        Employee employee = this.employeeService.getById(Long.parseLong(employeeId));
        
        
        if (employee == null) {
            return R.error("员工不存在,请重试!");
        } else if (! employee.getPassword().equals(DigestUtils.md5DigestAsHex(oldPwd.getBytes(StandardCharsets.UTF_8)))) {
            return R.error("原密码输入错误,请重试!");
        }
        
        
        employee.setPassword(DigestUtils.md5DigestAsHex(newPwd.getBytes(StandardCharsets.UTF_8)));
        boolean flag = this.employeeService.updateById(employee);
        
        
        Set<String> redisKey = this.redisTemplate.keys("Employee" + "*");
        this.redisTemplate.delete(redisKey);
        return flag ? R.success("密码更改成功！") : R.error("密码更改失败,请重试!");
    }
    
}
