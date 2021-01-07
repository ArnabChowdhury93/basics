package com.learning.basics.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.learning.basics.database.AttendanceDataRepository;
import com.learning.basics.models.AttendanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AroundAopAspect {

    @Autowired
    private AttendanceDataRepository attendanceDataRepository;


    @Around(value = "execution(* com.learning.basics.controller.AppController.addAttendance(..))")
    public Object addLeave(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        AttendanceData data = (AttendanceData) args[0];
        if(data.getHours()<5){
            attendanceDataRepository.save(data);
        }

        return pjp.proceed();
    }
}
