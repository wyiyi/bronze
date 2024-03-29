package com.amber.common.sensitive.impl;

import com.amber.common.sensitive.DataSensitiveHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * 自定义处理器注册 bean，”dataSensitiveHandler-“ 为固定前缀
 * 自定义后缀：对字符串进行 md5 摘要，仅在写入数据时执行操作
 * 实现加密和解密的方法
 */
@Component("dataSensitiveHandler-md5")
public class DataSensitiveMd5Handler implements DataSensitiveHandler {

    /**
     * 使用 md5 算法进行加密
     * @param str str
     * @return String
     */
    @Override
    public String encrypt(String str) {
        return DigestUtils.md5Hex(str);
    }

}
