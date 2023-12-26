package com.sf.common.redis.aop;


import com.sf.common.base.util.SpElUtils;
import com.sf.common.redis.annotation.RedissonLock;
import com.sf.common.redis.service.RedissonLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ky
 * @description 分布式锁（Redisson） AOP
 * @date 2023-12-13 16:10
 */
@Slf4j
@Aspect
@Order(0)
@Component
public class RedissonLockAspect {

    @Autowired
    private RedissonLockService lockService;

    @Around("@annotation(com.sf.common.redis.annotation.RedissonLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        String prefix = StringUtils.isBlank(redissonLock.prefixKey()) ? method.getDeclaringClass() + "#" + method.getName() : redissonLock.prefixKey();
        String key = SpElUtils.parseSpEl(method, joinPoint.getArgs(), redissonLock.key());
        return lockService.executeWithLockThrows(prefix + ":" + key, redissonLock.waitTime(), redissonLock.timeUnit(), joinPoint::proceed);
    }
}
