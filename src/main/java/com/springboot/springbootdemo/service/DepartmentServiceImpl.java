package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.dao.db2.DepartmentDao;
import org.hibernate.annotations.Cache;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    /**
     *
     * @author jiangxia
     * @date 2021/12/9 15:04
     * @param No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description: @Cacheable将方法的运行结果进行缓存
     * 以后如果再要相同的结果则直接从缓存中获取，不用调用方法
     * CacheManager：管理多个cache组件，对缓存真正的crud操作上是在
     * cache组件中，每一个缓存组件都有自己唯一的名字；
     * 几个属性：
     * CacheNames/value：指定缓存组件的名字
     * key：缓存数据时使用的key，可以使用它来指定，默认是使用方法参数的值
     *          可以使用spel表达式编写
     * keyGenerator：key的生成器：可以自己指定key的生成器的组件id
     * key/keyGenerator 二选一使用
     * CacheManager：指定缓存管理器；或者cacheResolve指定获取缓存解析器:二选一
     * condition：指定符合条件的情况下才进行缓存：比如condition="#id>0"只有id大于0时才进行缓存
     * unless：否定缓存，当unless指定的条件为true，返回的值就不进行缓存，可以获取到结果进行判断进行缓存，与condition情况相反，比如：unless="#{result==null}",即返回值不为空就进行缓存
     * sync：缓存是否使用异步模式
     *
     * 原理：
     * 1、自动配置类 CacheAutoConfiguration
     * 2、缓存的配置类：org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * 3、哪个配置类能生效 ：SimpleCacheConfiguration
     * 4、给容器中注册了一个cachemanager：concurrentmapcachemanager
     * 5、可以获取和创建concurrentmapcachemanager类型的缓存组件，他的作用将数据保存在concurrentmao中
     *
     * 运行流程：
     * @cacheable
     *  1、方法运行之前，先去查询缓存组件，按照cachenames指定的名字获取
     *      cachemanager先获取相应的缓存，第一次获取缓存如果没有cache组件会自动创建
     *  2、去cache中查找缓存的内容，使用一个key，默认就方法的参数
     *      key是按照某种策略生成的，默认是使用keygenerator生成的，默认使用
     *      simplekeygenerator生成key
     *  3、没有查到缓存就调用目标方法
     *  4、将目标方法返回的结果，放进缓存中
     *
     * @cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认是按照参数的值
     * 作为key去查询缓存
     * 如果没有就运行方法将结果放入缓存中，以后再来调用就可以直接使用缓存中的数据
     *
     * 核心：
     * 1、使用cachemanager concurrentmapcachemanager按照名字得到cache concurrentmapcache组件
     * 2、key使用keygenerator生成的，默认是simplekeygenerator
     *
     */
//    @Cacheable(cacheNames = "dep",keyGenerator = "mykeyGenerator",condition = "#a0>1",unless = "#a0=2")
    @Cacheable(cacheNames = "dep")
    public Department findByID(int id) {
        System.out.println("查询的数据是ID为"+id+"的数据");
        return departmentDao.findById(id);
    }

    /**
     *
     * @author jiangxia
     * @date 2021/12/9 20:57
     * @param ：No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description@CachePut:表示即调用方法，又更新缓存数据，同步更新缓存（需要key保持一直）
     * 1、运行时机：先调用目标方法
     * 2、将目标方法的结果缓存起来，即实在运行之后往缓存中放入数据
     * 因为缓存的默认key是传入参数的值，所以更新之后再查询
     * 会没有更新缓存中的数据， 所以需要在这里指定key的值
     * #department.id == #result.id
     * 但是cacheable不能使用result.id，因为cacheable是在运行之前的，而cacheput是在运行之后
     */
    @CachePut(value="dep",key="#department.id")
    public Department updateDepartment(Department department){
       System.out.println("更新信息："+department);
       departmentDao.updateDepartment(department);
       return department;
    }
    
    /**
     * 
     * @author jiangxia
     * @date 2021/12/9 21:24
     * @param ：No such property: code for class: Script1
     * @return No such property: code for class: Script1
     * @description @CacheEvict:缓存清除
     * 通过key指定需要清除缓存中的数据
     * allEntries = true 表示清除缓存中的所有数据
     * beforeInvocation = true 清除缓存的操作是否是在方法之前执行，默认是false，表示是在方法之后执行
     * 之前之后的区别就是：如果方法执行发生异常缓存是否被清除
     */
    @CacheEvict(value = "dep",key = "#id"/*,beforeInvocation = true*//*,allEntries = true*/)
    public boolean delDep(int id){
        boolean flag=false;
        try{
            departmentDao.deleteDepartmentById(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @RabbitListener(queues = "jiang.news")
    public void receive(Department department){
        System.out.println("收到消息："+department);
    }

    @RabbitListener(queues = "jiang")
    public void receiveMessage(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
