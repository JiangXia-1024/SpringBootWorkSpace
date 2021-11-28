package com.springboot.springbootdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /***
     * @Description: 设置缓存失效时间
     * @Param: []
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 根据key获取失效时间
     * @Param: [key]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }


    /***
     * @Description: 判断key是否存在
     * @Param: [key]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /***
     * @Description: 根据key删除缓存
     * @Param: [key] 可以传一个或者多个值
     * @return: void
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public void del(String... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /***
     * @Description: 获取缓存
     * @Param: [key]
     * @return: java.lang.Object
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public Object getValue(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    public Object hashget(String key,String  value){
        return redisTemplate.opsForHash().get(key,value);
    }


    /***
     * @Description: 获取hashKey对应的所有键值
     * @Param: [key]
     * @return: java.util.Map<java.lang.Object,java.lang.Object>
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public Map<Object,Object> hashMapGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /***
     * @Description:缓存存入
     * @Param: [key, value]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public boolean putValue(String key,Object item){
        try{
            redisTemplate.opsForValue().set(key,item);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hashset(String key,Map<String,Object> map){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: hashset 并设置时间
     * @Param: [key, map, time]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean hashSetAndExpire(String key,Map<String,Object> map,long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description:  向一张hash表中放入数据,如果不存在将创建
     * @Param: [key, item, value]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean hashSet(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 向一张hash表中放入数据,如果不存在将创建,如果已存在的hash表有时间,这里将会替换原有的时间
     * @Param: [key, item, value, time]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean hashSet(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if(time>0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 存缓存并且设置过期时间
     * @Param: [key, value, time]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean putValueAndSetExpire(String key,Object value,long time){
        try{
            if(time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                //time要大于0 如果time小于等于0 将设置无限期
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 设置redis递增
     * @Param: [key, delta：要增加几(大于0)]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long inc(String key,long delta){
        if(delta<=0){
            throw  new RuntimeException("redis 递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /***
     * @Description: 设置redis递减
     * @Param: [key, delta]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("redis 递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key,-delta);
    }

    /***
     * @Description: 删除hashset中的值
     * @Param: [key, item]
     * @return: void
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public void hdel(String key,Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    public boolean hashash(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /***
     * @Description: hash递增
     * @Param: [key, item, by]
     * @return: double
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /***
     * @Description: hash递减
     * @Param: [key, item, by]
     * @return: double
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 将数据放入set缓存
     * @Param: [key, values]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Description: 将set数据放入缓存，并设置过期时间
     * @Param: [key, time, values]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Description:  获取set缓存的长度
     * @Param: [key]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Description: 移除值为value的项
     * @Param: [key, values]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /***
     * @Description: 获取list缓存的内容
     * @Param: [key, start, end]
     * @return: java.util.List<java.lang.Object>
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /***
     * @Description: 获取list缓存的长度
     * @Param: [key]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /***
     *@Description: 过索引 获取list中的值
     * @Param: [key, index]
     * @return: java.lang.Object
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @Description: 将list放入缓存
     * @Param: [key, value]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 将list放入缓存
     * @Param: [key, value, time]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 将list放入缓存
     * @Param: [key, value]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * @Description: 将list放入缓存
     * @Param: [key, value, time]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * @Description: 根据索引修改list中的某条数据
     * @Param: [key, index, value]
     * @return: boolean
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Description: 移除N个值为value
     * @Param: [key, count, value]
     * @return: long
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

