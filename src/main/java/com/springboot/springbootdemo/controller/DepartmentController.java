package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangxia
 * @date 2021年12月09日 12:07
 */
@RestController
@RequestMapping(value = "/do/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //根据用户名查询数据
    @RequestMapping(value = "/dep", method = RequestMethod.GET)
    public Department department(@RequestParam(value = "id",required = true) int id){
        return departmentService.findByID(id);
    }

    @GetMapping("/depupdate")
    public Department updateDepartment(Department department){
        Department department1 = departmentService.updateDepartment(department);
        return  department1;
    }

    @RequestMapping(value = "/deldep", method = RequestMethod.DELETE)
    public String delDepartment(@RequestParam(value = "id",required = true) int id){
        if(departmentService.delDep(id)){
            return "success";
        }
        return "fail";
    }
}
