package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.Department;

/**
 * @author jiangxia
 * @date 2021年12月09日 12:03
 */
public interface DepartmentService {
    //根据id查询部门
    Department findByID(int id);
}
