package com.springboot.springbootdemo.controller.es;

import com.springboot.springbootdemo.bean.ESBean;
import com.springboot.springbootdemo.service.es.ESBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jiangxia
 * @date 2022年01月08日 10:07
 */
@RestController
@RequestMapping("testes")
public class EsBeanController {
    @Autowired
    ESBeanService esBeanService;

    @RequestMapping("savees")
    public void save(ESBean bean) {
        bean = new ESBean(1,"樱木花道",0,"樱木花道高中生，打篮球很厉害");
        esBeanService.save(bean);
    }

    @RequestMapping("findname/{name}")
    public List<ESBean> findByName(@PathVariable("name") String name){
        return esBeanService.findByName(name);
    }

    @RequestMapping("list")
    public String save() {
        List<ESBean> list = null;
        list = new ArrayList<>();
        String[] names = {"悟空","柯南","鸣人","博人","悟饭"};
        String[] descs = {"电视动画片名侦探柯南改编自青山刚昌创作的同名漫画作品","其中名侦探柯南警察学校篇Wild Police Story改编自青山刚昌新井隆广创作的漫画名侦探柯南警察学校篇Wild Police Story","该片由原东京电影新社负责动画制作","于1996年1月8日在读卖电视台首播","悟空是七龙珠里面最厉害的角色，也是很多人喜欢的角色"};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int a = random.nextInt(names.length);
            int b = random.nextInt(descs.length);
            ESBean esBean = new ESBean(i,names[a],i%2,descs[b]);
            list.add(esBean);
        }
        esBeanService.save(list);

        return "success";
    }

}
