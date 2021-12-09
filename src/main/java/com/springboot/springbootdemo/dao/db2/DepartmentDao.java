package com.springboot.springbootdemo.dao.db2;

import com.springboot.springbootdemo.bean.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

public interface DepartmentDao {
    /**
     * 根据查询数据
     *
     */
    @Select("select id,departmentName from department where id=#{id}")
    Department findById(@Param("id") int id);
}
