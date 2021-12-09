package com.springboot.springbootdemo.controller;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
