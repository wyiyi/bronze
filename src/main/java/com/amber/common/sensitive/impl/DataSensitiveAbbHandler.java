package com.amber.common.sensitive.impl;

import com.amber.common.sensitive.DataSensitiveHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义处理器注册 bean，”dataSensitiveHandler-“ 为固定前缀
 * 自定义后缀：abb 对字符串中间部分使用 `*` 遮挡，仅对读取到的数据执行操作
 * 实现加密和解密的方法
 */
@Component("dataSensitiveHandler-abb")
public class DataSensitiveAbbHandler implements DataSensitiveHandler {

    /**
     * 实现加密方法
     * @param str str
     * @return String
     */
    @Override
    public String encrypt(String str) {
        return str;
    }

    /**
     * 实现解密方法，对字符串中间部分使用 `*` 遮挡
     * @param str str
     * @return String
     */
    @Override
    public String decrypt(String str) {
//        return StringUtils.abbreviateMiddle(str, "****", str.length() - 4);
        int length = str.length();
        if (length < 2){
            return str;
        } else if (length % 2 == 0) {
            int midIndex = length / 2 - 1;
            return str.substring(0, midIndex) + "****" + str.substring(midIndex + 2);
        }else {
            int midIndex = length / 2;
            return str.substring(0, midIndex - 2) + "****" + str.substring(midIndex + 2);
        }
    }

}
