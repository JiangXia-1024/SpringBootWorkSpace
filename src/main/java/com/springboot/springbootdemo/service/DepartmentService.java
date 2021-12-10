package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.bean.User;

/**
 * @author jiangxia
 * @date 2021年12月09日 12:03
 */
public interface DepartmentService {
    //根据id查询部门
    Department findByID(int id);

    /**
     * 更新数据
     * @param department
     * @return
     */
    Department updateDepartment(Department department);

    /**
     *
     * @author jiangxia
     * @date 2021/12/9 21:27
     * @param No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description 删除数据
     */

    boolean delDep(int id);

}
