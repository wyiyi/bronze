package com.amber.common.sensitive;

/**
 * 存入数据时调用加密（encrypt）方法；读取数据时调用解密（ decrypt）方法
 */
public interface DataSensitiveHandler {

    /**
     * 在写入数据时对数据做的处理
     * 默认为不进行任何操作
     *
     * @param str 将要写入的数据
     * @return 实际写入的数据
     */
    default String encrypt(String str) {
        return str;
    }

    /**
     * 在读取数据时对数据做的处理
     * 默认为不进行任何操作
     *
     * @param str 实际读到的数据
     * @return 返回给调用者的数据
     */
    default String decrypt(String str) {
        return str;
    }

}
