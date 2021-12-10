package com.fastcampus.jblog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class BlogAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogAdvice.class);

    @Before("BlogPointcut.controllerPointcut()")
    public void beforeControllerLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        LOGGER.debug("[ 컨트롤러 ] " + method + "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.controllerPointcut()", returning = "returnObj")
    public void afterControllerLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            LOGGER.debug("[ 컨트롤러 ] " + method + "() 리턴값 : " + returnObj);
    }

    @Before("BlogPointcut.servicePointcut()")
    public void beforeServiceLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        LOGGER.debug("[ 서비스 ] " + method + "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.servicePointcut()", returning = "returnObj")
    public void afterServiceLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            LOGGER.debug("[ 서비스 ] " + method + "() 리턴값 : " + returnObj);
    }

    @Before("BlogPointcut.daoPointcut()")
    public void beforeDaoLog(JoinPoint jp) {
        String method = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        LOGGER.debug("[ 레파지토리 ] " + method + "() 매개변수 : " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "BlogPointcut.daoPointcut()", returning = "returnObj")
    public void afterDaoLog(JoinPoint jp, Object returnObj) {
        String method = jp.getSignature().getName();
        if(returnObj != null)
            LOGGER.debug("[ 레파지토리 ] " + method + "() 리턴값 : " + returnObj);
    }
}
