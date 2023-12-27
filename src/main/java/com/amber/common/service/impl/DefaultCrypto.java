package com.amber.common.service.impl;

import com.amber.common.entity.Algorithm;
import com.amber.common.service.ICrypto;
import com.amber.common.util.CryptoUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultCrypto implements ICrypto {

    private final static String KEY = "edcb87b4-68b1-466b-8f6d-256ef53e50f0";

    /**
     * 加密
     *
     * @param algorithm 加密算法
     * @param value     加密前的值
     * @param key       秘钥
     * @return 加密后的值
     */
    @Override
    public String encrypt(Algorithm algorithm, String value, String key) {
        String result;

        if (key == null || key.isEmpty()) {
            key = KEY;
        }

        switch (algorithm) {
            case MD5:
                result = CryptoUtil.encryptMd5Base64(value);
                break;
            case AES:
                result = CryptoUtil.encryptAesBase64(key, value);
                break;
            default:
                result = CryptoUtil.encryptAesBase64(key, value);
        }
        return result;
    }

    /**
     * 解密
     *
     * @param algorithm 解密算法
     * @param value     解密前的值
     * @param key       秘钥
     * @return 解密后的值
     */
    @Override
    public String decrypt(Algorithm algorithm, String value, String key) {
        String result;
        if (key == null || key.isEmpty()) {
            key = KEY;
        }

        try {
            switch (algorithm) {
                case MD5:
                    log.debug("该算法不支持解密");
                    result = "";
                    break;
                case AES:
                    result = CryptoUtil.decryptAesBase64(key, value);
                    break;
                default:
                    result = CryptoUtil.decryptAesBase64(key, value);
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("值：‘" + value + "’不支持解密");
            result = "";
        }

        return result;

    }

}
