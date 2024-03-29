package com.amber.common.sensitive.impl;

import com.amber.common.sensitive.DataSensitiveHandler;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

/**
 * 敏感数据处理器：使用 * 遮挡明文中间部分，保留前后内容。
 * <p>
 * 仅在读取明文数据并返回时，进行遮挡处理；
 * 存入明文数据时，不对存入内容进行变更。
 * <p>
 * 遮挡方式为：<p>
 * 1. 明文长度为 1 时，不遮挡<p>
 * 2. 明文长度为 2 时，遮挡第二位<p>
 * 3. 明文长度大于 2 时，将明文分成三部分，遮挡中间部分；不能整除时，尽可能使遮挡部分较多<p>
 * 遮挡后内容长度与明文保持一致。
 * 如：<p>
 * 明文                   | 遮挡后<p>
 * 'a'                   | 'a'<p>
 * 'ab'                  | 'a*'<p>
 * 'abc'                 | 'a*c'<p>
 * 'abcd'                | 'a**d'<p>
 * '13012345678'         | '130*****678'<p>
 * '123456789012345678'  | '123456******345678'<p>
 * '这是一段测试文字'       | '这是****文字'<p>
 * 注册 bean 使用 ”dataSensitiveHandler-“ 固定前缀，abb 为后缀，意为 abbreviation
 */
@Component("dataSensitiveHandler-abb")
public class DataSensitiveAbbHandler implements DataSensitiveHandler {

    private static final char MASK = '*';

    /**
     * 实现解密方法，对字符串中间部分使用 `*` 遮挡
     *
     * @param str str
     * @return String
     */
    @Override
    public String decrypt(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        int len = str.length();
        switch (len) {
            case 1:
                return str;
            case 2:
                return str.substring(0, 1) + MASK;
            default:
                int oriLen = len / 3;
                int maskLen = len - oriLen * 2;
                return str.substring(0, oriLen) +
                        StringUtils.repeat(MASK, maskLen) +
                        str.substring(oriLen + maskLen);
        }
    }

}
