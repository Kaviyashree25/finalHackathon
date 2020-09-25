package com.stackroute.PostService.aspect;

import com.stackroute.PostService.controller.PostController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect
{
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @Before(value = "execution(* com.stackroute.PostService.controller.PostController .*(..))")
    public void beforeAdviceMethod(JoinPoint joinPoint)
    {
        logger.info("Inside the before advice.");
        logger.info("Target Method Object: " + joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.stackroute.PostService.controller.PostController .*(..))")
    public void afterAdviceMethod(JoinPoint joinPoint)
    {
        logger.info("Inside the after advice.");
        logger.info("Target Method Object: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.stackroute.PostService.controller.PostController .*(..))", returning = "retval")
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object retval)
    {
        logger.info("Inside the after returning advice.");
        logger.info("Target Method Object: " + joinPoint.getSignature().getName());
        logger.info("Object returned: " + retval);
    }

    @AfterThrowing(value = "execution(* com.stackroute.PostService.controller.PostController .*(..))", throwing = "exception")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Exception exception)
    {
        logger.info("Inside the after throwing advice.");
        logger.info("Target Method Object: " + joinPoint.getSignature().getName());
        logger.info("Exception thrown: " + exception);
    }

}
