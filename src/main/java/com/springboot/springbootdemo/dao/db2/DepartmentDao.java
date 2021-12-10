package com.springboot.springbootdemo.dao.db2;

import com.springboot.springbootdemo.bean.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

public interface DepartmentDao {
    /**
     * 根据查询数据
     *
     */
    @Select("select id,departmentName from department where id=#{id}")
    Department findById(@Param("id") int id);

    /**
     * 更新数据
     */
    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM department WHERE id = #{id}")
    void deleteDepartmentById(@Param("id") int id);
}
