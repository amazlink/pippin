package io.github.rothschil.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略 Token 验证
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/11/6 - 10:16
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreTokenCheck {

    /**
     * 默认 为 true，需要检查
     *
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @date 2019/11/7-20:10
     **/
    boolean required() default true;
}
