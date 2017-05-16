package kys24.goods.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Duolaimon
 *         17-5-2 下午7:23
 */
@Aspect
@Component
public class ShopAspect {

    @Pointcut("execution(public * kys24.goods.service.impl.*(..))")
    public void path() {
    }

    @Before("path()")
    public void checkIsUpdate() {

    }
}
