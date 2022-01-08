package com.springboot.springbootdemo.service.es;

import com.springboot.springbootdemo.bean.ESBean;
import com.springboot.springbootdemo.dao.es.EsBeanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年01月08日 9:59
 */
@Service
public class ESBeanServiceImpl implements ESBeanService {

    @Autowired
    EsBeanDao esBeanDao;

    @Override
    public Iterable<ESBean> findAll() {
        return esBeanDao.findAll();
    }

    @Override
    public void save(List<ESBean> list) {
        esBeanDao.saveAll(list);
    }

    @Override
    public void save(ESBean esBean) {
        esBeanDao.save(esBean);
    }

    @Override
    public List<ESBean> findByName(String name) {
        return esBeanDao.findByName(name);
    }

    @Override
    public List<ESBean> findByDesc(String desc) {
        return esBeanDao.findByDesc(desc);
    }
}
