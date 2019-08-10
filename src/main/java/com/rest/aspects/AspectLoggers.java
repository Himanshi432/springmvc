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
}
