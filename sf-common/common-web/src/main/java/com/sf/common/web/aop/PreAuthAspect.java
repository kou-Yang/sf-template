package com.sf.common.web.aop;

import com.sf.common.base.exception.BusinessException;
import com.sf.common.base.exception.CommonErrorEnum;
import com.sf.common.base.util.SpringContextUtils;
import com.sf.common.web.annotation.PreAuth;
import com.sf.common.web.auth.BaseAuthFun;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

/**
 * @author ky
 * @description 鉴权 AOP
 * @date 2024-05-27 16:24
 */
@Aspect
@Component
public class PreAuthAspect {

    @Autowired
    private ApplicationContext applicationContext;

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ClassPathScanningCandidateComponentProvider PROVIDER;

    static {
        PROVIDER = new ClassPathScanningCandidateComponentProvider(false);
        PROVIDER.addIncludeFilter(new AssignableTypeFilter(BaseAuthFun.class));
    }

    @Pointcut("@annotation(com.sf.common.web.annotation.PreAuth) || @within(com.sf.common.web.annotation.PreAuth)")
    private void pointcut() {

    }

    @Around("pointcut()")
    public Object proAuth(ProceedingJoinPoint point) throws Throwable {
        if (handleAuth(point)) {
            return point.proceed();
        }
        throw new BusinessException(CommonErrorEnum.NO_AUTH_ERROR);
    }

    private Boolean handleAuth(ProceedingJoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        PreAuth preAuth = method.getAnnotation(PreAuth.class);
        if (Objects.isNull(preAuth)) {
            preAuth = point.getTarget().getClass().getAnnotation(PreAuth.class);
        }
        String condition = preAuth.value();
        if (StringUtils.isNotBlank(condition)) {
            Expression expression = EXPRESSION_PARSER.parseExpression(condition);
            StandardEvaluationContext context = getEvaluationContext(method, point.getArgs());
            return expression.getValue(context, Boolean.class);
        }
        return false;
    }

    private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
        // 初始化spEL表达式上下文
        StandardEvaluationContext context = new StandardEvaluationContext(getAuthFun());
        // 设置表达式支持spring bean
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        // 解析参数名
        String[] params = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        return context;
    }

    @SneakyThrows
    private BaseAuthFun getAuthFun() {
        Set<BeanDefinition> beanDefinitionSet = PROVIDER.findCandidateComponents("com.");
        if (CollectionUtils.isNotEmpty(beanDefinitionSet)) {
            for (BeanDefinition beanDefinition : beanDefinitionSet) {
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                if (clazz.getSuperclass().equals(BaseAuthFun.class)) {
                    return (BaseAuthFun) SpringContextUtils.getBean(clazz);
                }
            }
        }
        return new BaseAuthFun();
    }
}
