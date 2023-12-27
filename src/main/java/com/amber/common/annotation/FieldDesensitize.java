package com.amber.common.annotation;

import com.amber.common.service.IDesensitizer;
import com.amber.common.service.impl.DefaultDesensitizer;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface FieldDesensitize {

    /**
     * 填充值
     *
     * @return
     */
    String fillValue() default "*";

    /**
     * 脱敏器
     *
     * @return
     */
    Class<? extends IDesensitizer> desensitizer() default DefaultDesensitizer.class;
}
