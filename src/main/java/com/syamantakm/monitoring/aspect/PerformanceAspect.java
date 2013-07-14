package com.syamantakm.monitoring.aspect;

import com.syamantakm.monitoring.annotation.Latency;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Syamantak Mukhopadhyay
 */
@Aspect
public class PerformanceAspect {
    private static final Logger LOGGER = Logger.getLogger(PerformanceAspect.class);

    @Around("execution(public * *(..)) " +
            "&& @annotation(latency)")
    public Object advice(ProceedingJoinPoint pjp, Latency latency) throws Throwable {
        if (latency.enabled()) {
            long startTIme = System.currentTimeMillis();
            Object result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            LOGGER.info(String.format("Target :%s, Execution time  : %s ms", pjp.getTarget().toString(), endTime - startTIme));
            return result;
        } else {
            return pjp.proceed();
        }

    }
}
