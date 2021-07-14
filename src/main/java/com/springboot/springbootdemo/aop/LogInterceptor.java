package com.springboot.springbootdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

/**
 * the simple demo for aop
 * author:jiangxia
 * date:2021-07-13
 */
@Aspect
@Component
public class LogInterceptor {

    //抽取公共的切入点表达式
    //1、本类引用
    //2、其他的切面引用
    //@Pointcut("execution(方法得全类名路径:例如  包名+类名+方法)")
    //@Pointcut("execution(public * com.springboot.springbootdemo.service..*.findAll(..))")
    // 定义一个切点：所有被RequestMapping注解修饰的方法会织入advice
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aopMethod(){

    }

    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    @Before("aopMethod()")
    public void before(){
        System.out.println("aopMethod start");
    }

    @Around("aopMethod()")
    public Object Around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("aopMethod Around");
        SourceLocation sl = jp.getSourceLocation();
        Object ret = jp.proceed();
        System.out.println(jp.getTarget());
        return ret;
    }

    @After("aopMethod()")
    public void after() {
        System.out.println("aopMethod after");
    }

    @AfterReturning("aopMethod()")
    public void AfterReturning() {
        System.out.println("aopMethod AfterReturning");
    }

    @AfterThrowing("aopMethod()")
    public void AfterThrowing() {
        System.out.println("aopMethod AfterThrowing");
    }


//    @Before("aopMethod()")
//    public void before2(JoinPoint jp) {
//        Object[] args = jp.getArgs();
//        System.out.println("userId11111: " + (Integer)args[0]);
//        System.out.println(jp.getTarget());
//        System.out.println(jp.getThis());
//        System.out.println(jp.getSignature());
//        System.out.println("aopMethod start");
//    }
}
