package com.android.stru.demos.fragmentrelated;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TimeConsumingAspect {
    private final String TAG = "TimeConsumingAspect";

    // Pointcut的功能 是从众多的 JoinPoint中找到指定的执行点；
    @Pointcut("execution( * onTest**(** )")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        Log.d(TAG, "@Before");
    }

    @Around("pointcut()")
    public void timeConsumingTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //方法执行前
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        TimeConsumingTrace annotation = methodSignature.getMethod().getAnnotation(TimeConsumingTrace.class);

        int key = annotation.key();
        Log.d(TAG, "key： " + key);

        String value = annotation.value();
        Log.d(TAG, "value： " + value);

        String name = methodSignature.getName();
        Log.d(TAG, "执行方法： " + name);

        long startTime = System.currentTimeMillis();

        proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        Log.d(TAG, "总共耗时： " + (endTime - startTime));
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        Log.d(TAG, "@After");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point, Object returnValue) {
        Log.d(TAG, "@AfterReturning: " + returnValue);
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.e(TAG, "@afterThrowing");
        Log.e(TAG, "ex = " + ex.getMessage());
    }
}