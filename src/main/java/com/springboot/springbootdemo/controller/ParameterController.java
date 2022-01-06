package com.springboot.springbootdemo.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangxia
 * @date 2022年01月06日 19:40
 * des:请求参数注解
 */
@RestController
public class ParameterController {
//    @Autowired
//    WebConfig webConfig;

    //    请求参数注解@RequestParam
    @RequestMapping("/testequestparam")
    public String testequestParam(@RequestParam("name") String name){
        return name+",你好啊！";
    }

    @RequestMapping("/testequestparam1")
    public String testequestParam1(@RequestParam(value="name",required = false) String name){
        return name+",你好啊！";
    }

    @RequestMapping("/testequestparam2")
    public String testequestParam2(@RequestParam(defaultValue = "defaultValue") String name){
        return name+",你好啊！";
    }
    //    @PathVariable路径变量注解
    @GetMapping("/car/{id}/user/{name}/band/{band}")
    //http://localhost:8081/share/car/1/user/李四/band/比亚迪/
    public String testPathVariable(@PathVariable("id") Integer id,
                                   @PathVariable("name") String name,
                                   @PathVariable("band")String band,
                                   @PathVariable Map<String,String> pv){
        return "返回的车是"+name+"的名下id为"+id+"的车,车子的品牌是"+band;
    }

    // @PathVariable Map<String,String> pv 所有的请求参数
    @GetMapping("/map/car/{id}/user/{name}/band/{band}")
    public Map<String,Object> testPathVariableMap(@PathVariable("id") Integer id,
                                                  @PathVariable("name") String name,
                                                  @PathVariable("band")String band,
                                                  @PathVariable Map<String,String> pv){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("band",band);
        map.put("pv",pv);
        return map;
    }

    //@RequestHeader获取请求头信息useragent 所有的请求头信息header(格式和pv一样 要求string string的map)
    //http://localhost:8081/share/map1/car/1/user/李四/band/比亚迪/
    @GetMapping("/map1/car/{id}/user/{name}/band/{band}")
    public Map<String,Object> testPathVariableMap1(@PathVariable("id") Integer id,
                                                   @PathVariable("name") String name,
                                                   @PathVariable("band")String band,
                                                   @PathVariable Map<String,String> pv,
                                                   @RequestHeader("User-Agent") String useragent,
                                                   @RequestHeader Map<String,String> header) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("band", band);
        map.put("pv", pv);
        map.put("useragent",useragent);
        map.put("header",header);
        return map;
    }

    //RequestParam获取请求参数的值
    //http://localhost:8081/share/map2/car/1/user/李四/band/比亚迪/?price=590000&inters=games&inters=football
    @GetMapping("/map2/car/{id}/user/{name}/band/{band}")
//    @GetMapping("/map2/car/{id}/user/{name}/band/{band}?price=590000&inters=games&inters=football")都可以
    public Map<String,Object> testPathVariableMap2(@PathVariable("id") Integer id,
                                                   @PathVariable("name") String name,
                                                   @PathVariable("band")String band,
                                                   @PathVariable Map<String,String> pv,
                                                   @RequestHeader("User-Agent") String useragent,
                                                   @RequestHeader Map<String,String> header,
                                                   @RequestParam("price") Integer price,
                                                   @RequestParam("inters") List<String> inters,
                                                   @RequestParam Map<String,String> params) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("band", band);
        map.put("pv", pv);
        map.put("useragent",useragent);
        map.put("header",header);
        map.put("price",price);
        map.put("inters",inters);
        map.put("params",params);
        return map;
    }

    //CookieValue 获取cookie的值
    @GetMapping("/cookie")
    public Map<String,Object> testPathVariableMap3( @CookieValue("_ga") String _ga,
                                                    @CookieValue("_ga") Cookie cookie){
        Map<String,Object> map = new HashMap<>();
        map.put("cookie",cookie);
        System.out.println(cookie.getName()+"========"+cookie.getValue());
        return map;
    }

    @PostMapping("/share/save")
    public Map postMethod(@RequestBody String content){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }

    //    1、语法：请求路径：/phone/sell;low=34;band=hw,xiaomi,oppo
//    2、SpringBoot默认是禁用了矩阵变量的功能
//        手动开启：原理：对于路径的处理是通过UrlPathHelper进行解析
//            removesemicolonconten（移除分号内容）支持矩阵变量的
//    3、矩阵变量必须有url路径变量才能被解析
    @GetMapping("/phone/{path}")
    public Map carsSell(@MatrixVariable("id") Integer id,
                        @MatrixVariable("band") List<String> brand,
                        @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();

        map.put("id",id);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    //http://localhost:8081/share/person/age=23/age=32
    @GetMapping("/person/{pid}/{empid}")
    public Map person(@MatrixVariable(value = "age",pathVar = "pid") Integer personAge,@MatrixVariable(value = "age",pathVar = "empid") Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("personAge",personAge);
        map.put("empAge",empAge);
        return map;
    }
}