package com.learning.basics.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.learning.basics.database.AttendanceDataRepository;
import com.learning.basics.models.AttendanceData;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
public class AroundAopAspect {

    @Autowired
    private AttendanceDataRepository attendanceDataRepository;

    @Pointcut("execution(* com.learning.basics.controller.AppController.addAttendance(..))")
    public void leave(){}


    @Around("leave()")
    public Object addLeave(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        AttendanceData data = (AttendanceData) args[0];
        if(data.getHours()<5){
            attendanceDataRepository.save(data);
        }
        return pjp.proceed();
    }


}
