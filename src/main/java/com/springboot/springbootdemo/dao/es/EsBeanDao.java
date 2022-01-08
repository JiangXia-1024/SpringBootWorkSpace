package com.springboot.springbootdemo.dao.es;

import com.springboot.springbootdemo.bean.ESBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年01月08日 9:56
 * //继承CrudRepository，第一个泛型是实体类类型，第二个泛型是id的类型
 */
public interface EsBeanDao extends CrudRepository<ESBean,Long> {
    //根据name查询
    List<ESBean> findByName(String name);

    //根据name或者desc查询
    List<ESBean> findByDesc(String desc);
}
