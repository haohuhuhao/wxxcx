package com.hh.wx.xcx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataAuthorAop {

	@Pointcut("@annotation(com.hh.wx.xcx.annotation.DataAuthor)")  
	public  void checkDataAuthor() {  
	}  
	
	
	// 在这里定义@CheckNotNull的增强方法
	@Around("checkDataAuthor()")
    public Object methodsAnnotatedWithCheckNotNull(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("sssss");
        return joinPoint.proceed();
    }
}
