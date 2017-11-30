package com.jeeva.rest;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Aspect
@Component
public class AuditableAspect {

    @After("@annotation(auditable)")
    @Transactional
    public void audit(JoinPoint joinPoint, RestAnnotations.Auditable auditable) throws Throwable {
        System.out.println(joinPoint.getSignature() + " audited >> " + auditable.action());
    }

}
