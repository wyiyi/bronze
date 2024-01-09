package com.amber.common.sensitive;

/**
 * 实现 DataSensitiveHandler 接口：加密（encrypt）、解密（ decrypt）接口
 */
public interface DataSensitiveHandler {

    /**
     * 在写入数据时对数据做的处理
     * @param str str
     * @return String
     */
    String encrypt(String str);

    /**
     * 在读取数据时对数据做的处理
     * @param str str
     * @return String
     */
    String decrypt(String str);

}
