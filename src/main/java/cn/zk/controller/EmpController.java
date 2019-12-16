package cn.zk.controller;

import cn.zk.dao.DepartmentDao;
import cn.zk.dao.EmployeeDao;
import cn.zk.pojo.Department;
import cn.zk.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmpController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees= employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "list";
    }
    //点击添加员工，跳转到添加员工的页面
    @GetMapping(value = "/emp")
    public String toAddEmp(Model model){
        //查询所有部门信息，在下拉列表显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "add";
    }
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @GetMapping("/updateEmp/{id}")
    public String updateEmp(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp",employee);
        model.addAttribute("depts",departments);
        return "update";
    }
    @PostMapping("/update")
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
