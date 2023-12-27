package com.amber.common.annotation;

import com.amber.common.entity.Algorithm;
import com.amber.common.service.ICrypto;
import com.amber.common.service.impl.DefaultCrypto;

import java.lang.annotation.*;

/**
 * @date 2021-11-16
 * @apiNote 字段加密
 */

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface FieldEncrypt {

    /**
     * 秘钥
     *
     * @return
     */
    String key() default "";

    /**
     * 加密解密算法
     *
     * @return
     */
    Algorithm algorithm() default Algorithm.AES;

    /**
     * 加密解密器
     *
     * @return
     */
    Class<? extends ICrypto> iCrypto() default DefaultCrypto.class;

}
