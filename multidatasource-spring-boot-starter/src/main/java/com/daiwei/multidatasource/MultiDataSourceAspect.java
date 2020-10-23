package com.daiwei.multidatasource;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MultiDataSourceAspect {

    // 定义切入点
    @Pointcut("@annotation(com.daiwei.multidatasource.MultiDataSource)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        MultiDataSource dataSource = method.getAnnotation(MultiDataSource.class);
        String preDsName = MultiDynamicDataSourceContextHolder.getDataSourceType(); // 解决嵌套问题
        if (dataSource != null) {
            MultiDynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }
        try {
            return point.proceed();
        } finally {
            // 切换会上一方法调用位置的源
            MultiDynamicDataSourceContextHolder.setDataSourceType(preDsName);
        }
    }
}