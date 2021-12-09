package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.dao.db2.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangxia
 * @date 2021年12月09日 12:05
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public Department findByID(int id) {
        return departmentDao.findById(id);
    }
}
