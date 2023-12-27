package com.amber.common.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Algorithm {

    /**
     * 不可逆加密 MD5
     * 对称加密  AES （推荐；速度快、可解密）
     */
    MD5, AES;

}
