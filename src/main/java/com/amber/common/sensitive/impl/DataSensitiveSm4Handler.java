package com.amber.common.sensitive.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.amber.common.sensitive.DataSensitiveHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * 自定义处理器注册 bean，”dataSensitiveHandler-“ 为固定前缀
 * 自定义后缀：sm4 使用国密 SM4 算法进行对称加解密，在写入及读取时均执行
 * 实现加密和解密的方法
 */
@ConditionalOnClass(name = "org.bouncycastle.crypto.Digest")
@Component("dataSensitiveHandler-sm4")
public class DataSensitiveSm4Handler implements DataSensitiveHandler {

    private static final SymmetricCrypto SM4 = SmUtil.sm4();

    /**
     * 使用国密 SM4 算法进行加密
     * @param str str
     * @return String
     */
    @Override
    public String encrypt(String str) {
        return SM4.encryptHex(str);
    }

    /**
     * 使用国密 SM4 算法进行解密
     * @param str str
     * @return String
     */
    @Override
    public String decrypt(String str) {
        return SM4.decryptStr(str, CharsetUtil.CHARSET_UTF_8);
    }

}
