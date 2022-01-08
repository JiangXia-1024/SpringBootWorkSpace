package com.springboot.springbootdemo.service.es;

import com.springboot.springbootdemo.bean.ESBean;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年01月08日 9:58
 */
public interface ESBeanService {
    Iterable<ESBean> findAll();

    void save(List<ESBean> list);

    void save(ESBean esBean);

    List<ESBean> findByName(String name);

    List<ESBean> findByDesc(String desc);
}
