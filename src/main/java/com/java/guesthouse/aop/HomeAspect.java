package com.java.guesthouse.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HomeAspect {
    public static Logger logger = Logger.getLogger(HomeAspect.class.getName());
    public static final String logMsg = "LogMsg==================";

    @Around(value = "within(com.java..*)")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;

        try {

            logger.info(logMsg + joinPoint.getTarget().getClass().getName() + "\t\t" + joinPoint.getSignature().getName());
            obj = joinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
        return obj;

    }

}
