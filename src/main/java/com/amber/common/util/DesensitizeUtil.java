package com.amber.common.util;

import java.util.regex.Pattern;

public class DesensitizeUtil {

    /**
     * 手机号码匹配
     */
    public static final String PHONE_REG = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    /**
     * 邮箱email
     */
    public static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 银行卡卡号位数
     */
    public final static String BANK_CARD_NUMBER = "^\\d{16}|\\d{19}$";
    /**
     * 身份证号码位数限制
     */
    public final static String ID_CARD = "^\\d{15}|(\\d{17}[0-9,x,X])$";


    /**
     * 敏感数据处理
     *
     * @param info 要加密的字符串
     */
    public static String replaceSecretInfo(String info, String fillValue) {

        if (info.isEmpty()) {
            return "";
        }
        String result;
        int infoLength = info.length();
        if (infoLength == 1) {
            result = "*";
        } else if (infoLength == 2) {
            result = info.charAt(0) + "*";
        } else {
            double tempNum = (double) infoLength / 3;
            int num1 = (int) Math.floor(tempNum);
            int num2 = (int) Math.ceil(tempNum);
            int num3 = infoLength - num1 - num2;
            String star = "";
            for (int i = 0; i < num2; i++) {
                star = star.concat(fillValue);
            }

            String regex = "(.{" + num1 + "})(.{" + num2 + "})(.{" + num3 + "})";
            String replacement = "$1" + star + "$3";
            result = info.replaceAll(regex, replacement);
        }
        return result;
    }


    //银行账户：显示前六后四，范例：622848******4568
    public static String encryptBankAcct(String bankAcct, String replacement) {
        if (bankAcct == null) {
            return "";
        }
        return replaceBetween(bankAcct, 6, bankAcct.length() - 4, replacement);
    }

    //身份证号码：显示前六后四，范例：110601********2015
    public static String encryptIdCard(String idCard, String replacement) {
        if (idCard == null) {
            return "";
        }
        return replaceBetween(idCard, 6, idCard.length() - 4, replacement);
    }

    //客户email：显示前二后四，范例：abxx@xxx.com
    public static String encryptEmail(String email, String replacement) {
        //判断是否为邮箱地址
        if (email == null || !Pattern.matches(EMAIL_REG, email)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(email);
        //邮箱账号名只显示前两位
        int at_position = sb.indexOf("@");
        if (at_position > 2) {
            sb.replace(2, at_position, repeat(replacement, at_position - 2));
        }
        //邮箱域名隐藏
        int period_position = sb.lastIndexOf(".");
        sb.replace(at_position + 1, period_position, repeat(replacement, period_position - at_position - 1));
        return sb.toString();
    }

    //手机：显示前三后四，范例：189****3684
    public static String encryptPhoneNo(String phoneNo, String replacement) {
        if (phoneNo == null) {
            return "";
        }
        return replaceBetween(phoneNo, 3, phoneNo.length() - 4, replacement);
    }

    /**
     * 对敏感信息进行处理。使用正则表达式判断使用哪种规则
     *
     * @param sourceStr 需要处理的敏感信息
     * @return String
     * @author tyg
     * @date 2018年5月5日下午3:59:28
     */
    public static String encryptSensitiveInfo(String sourceStr, String replacement) {
        if (sourceStr == null) {
            return "";
        }
        if (Pattern.matches(PHONE_REG, sourceStr)) {
            return encryptPhoneNo(sourceStr, replacement);
        } else if (Pattern.matches(EMAIL_REG, sourceStr)) {
            return encryptEmail(sourceStr, replacement);
        } else if (Pattern.matches(BANK_CARD_NUMBER, sourceStr)) {
            return encryptBankAcct(sourceStr, replacement);
        } else if (Pattern.matches(ID_CARD, sourceStr)) {
            return encryptIdCard(sourceStr, replacement);
        } else {
            return replaceSecretInfo(sourceStr, replacement);
        }
    }

    /**
     * 将字符串开始位置到结束位置之间的字符用指定字符替换
     *
     * @param sourceStr   待处理字符串
     * @param begin       开始位置
     * @param end         结束位置
     * @param replacement 替换字符
     * @return
     */
    private static String replaceBetween(String sourceStr, int begin, int end, String replacement) {
        if (sourceStr == null) {
            return "";
        }
        if (replacement == null) {
            replacement = "*";
        }
        int replaceLength = end - begin;
        if (!replacement.isEmpty() && replaceLength > 0) {
            StringBuilder sb = new StringBuilder(sourceStr);
            sb.replace(begin, end, repeat(replacement, replaceLength));
            return sb.toString();
        } else {
            return sourceStr;
        }
    }

    /**
     * 构建重复字符
     *
     * @param value
     * @param count
     * @return
     */
    private static String repeat(String value, int count) {
        String result = "";
        if (count > 0 && value != null && !value.isEmpty()) {
            for (int i = 0; i < count; i++) {
                result = result.concat(value);
            }
        }

        return result;
    }


}
