package com.rest.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectApiMeter {

    int countControllerGETClient = 0;
    int countControllerGETAddress = 0;
    int countControllerGETRole = 0;
    int countControllerPUTClient = 0;
    int countControllerPUTAddress = 0;
    int countControllerPUTRole = 0;
    int countControllerPOSTClient = 0;
    int countControllerPOSTAddress = 0;
    int countControllerDELETEClient = 0;
    int countControllerDELETEAddress = 0;


    @After("execution(* com.rest.controller.AddressController.getAddress(..))")
    public void aftermethodcounterAddress(JoinPoint joinPoint){
        countControllerGETAddress++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerGETAddress + " times");
    }

    @After("execution(* com.rest.controller.ClientController.getClient(..))")
    public void aftermethodcounterClient(JoinPoint joinPoint){
        countControllerGETClient++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerGETClient + " times");
    }

    @After("execution(* com.rest.controller.RoleController.getRoles(..))")
    public void aftermethodcounterRole(JoinPoint joinPoint){
        countControllerGETRole++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerGETRole + " times");
    }

    @After("execution(* com.rest.controller.AddressController.updateAddress(..))")
    public void aftermethodcounterAddressupdate(JoinPoint joinPoint){
        countControllerPUTAddress++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerPUTAddress + " times");
    }

    @After("execution(* com.rest.controller.ClientController.updateClient(..))")
    public void aftermethodcounterClientupdate(JoinPoint joinPoint){
        countControllerPUTClient++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerPUTClient + " times");
    }

    @After("execution(* com.rest.controller.RoleController.updateRoleDetails(..))")
    public void aftermethodcounterRoleupdate(JoinPoint joinPoint){
        countControllerPUTRole++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerPUTRole + " times");
    }

    @After("execution(* com.rest.controller.AddressController.addNewAddress(..))")
    public void aftermethodcounterAddressPOST(JoinPoint joinPoint){
        countControllerPOSTAddress++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerPOSTAddress + " times");
    }

    @After("execution(* com.rest.controller.ClientController.addClient(..))")
    public void aftermethodcounterClientPOST(JoinPoint joinPoint){
        countControllerPOSTClient++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerPOSTClient + " times");
    }

    @After("execution(* com.rest.controller.AddressController.deleteAddress(..))")
    public void aftermethodcounterAddressDELETE(JoinPoint joinPoint){
        countControllerDELETEAddress++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerDELETEAddress + " times");
    }

    @After("execution(* com.rest.controller.ClientController.DeleteClient(..))")
    public void aftermethodcounterClientDELETE(JoinPoint joinPoint){
        countControllerDELETEClient++;
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called for execution "
                + countControllerDELETEClient + " times");
    }
}
