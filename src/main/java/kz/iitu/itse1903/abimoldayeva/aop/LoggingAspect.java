package kz.iitu.itse1903.abimoldayeva.aop;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* kz.iitu.itse1903.abimoldayeva.service.*.*(..))")
    public void callAtAllClientMethods(){}

    @Before("callAtAllClientMethods()")
    public void logBeforeAllClientMethods(JoinPoint jp){
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        log.info("before " + jp + ", args=[" + args + "]");
    }

    @After("callAtAllClientMethods()")
    public void logAfterAllClientMethods(JoinPoint jp){
        String methodName = jp.getSignature()
                .getName();
        log.info("after - method name: " + methodName);
    }
}
