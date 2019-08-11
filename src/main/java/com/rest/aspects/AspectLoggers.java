package com.rest.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLoggers {

    @Before("execution(* com.rest.manager.*.*(..))")
    public void beforeMethodInvocation(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.manager.*.*(..))")
    public void afterMethodInvocation(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }

    @Before("execution(* com.rest.controller.*.*(..))")
    public void beforeMethodInvocationcontroller(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.controller.*.*(..))")
    public void afterMethodInvocationcontroller(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }

    @Before("execution(* com.rest.clients.*.*(..))")
    public void beforeMethodInvocationMongo(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.clients.*.*(..))")
    public void afterMethodInvocationMongo(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }

    @Before("execution(* com.rest.exceptions.*.*(..))")
    public void beforeMethodInvocationExceptions(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.exceptions.*.*(..))")
    public void afterMethodInvocationExceptions(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }


    @Before("execution(* com.rest.dao.*.*(..))")
    public void beforeMethodInvocationDAO(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.dao.*.*(..))")
    public void afterMethodInvocationDAO(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }

    @Before("execution(* com.rest.utils.*.*(..))")
    public void beforeMethodInvocationTPCConstants(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution.");
    }

    @After("execution(* com.rest.utils.*.*(..))")
    public void afterMethodInvocationTPCConstants(JoinPoint joinPoint){
        System.out.println("Method " + joinPoint.getSignature().getName() + " has finished execution.");
    }

}
