package com.amber.common.enums;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public enum SensitiveTypeEnum {
    CHINESE_NAME("chinese_name", "(?<=.{1}).", "*"),
    HIDDEN("hidden", ".", ""),
    ID_CARD("id_card", "(?<=\\w{3})\\w(?=\\w{4})", "*"),
    FIXED_PHONE("fixed_phone", "(?<=\\w{3})\\w(?=\\w{2})", "*"),
    MOBILE_PHONE("mobile_phone", "(?<=\\w{3})\\w(?=\\w{4})", "*"),
    ADDRESS("address", "(.{5}).+(.{4})", "$1*****$2"),
    EMAIL("email", "(\\w+)\\w{3}@(\\w+)", "$1***@$2"),
    BANK_CARD("bank_card", "(?<=\\w{4})\\w(?=\\w{4})", "*"),
    CNAPS_CODE("cnaps_code", "(?<=\\w{4})\\w(?=\\w{4})", "*"),
    DEFAULT_TYPE("default_type", ".", "*");

    private final String code;

    private final String pattern;

    private final String targetChar;

    SensitiveTypeEnum(String code, String pattern, String targetChar) {
        this.code = code;
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getTargetChar() {
        return this.targetChar;
    }

    public String getCode() {
        return this.code;
    }

    public static SensitiveTypeEnum getByCode(String code) {
        if (StringUtils.isNotEmpty(code))
            for (SensitiveTypeEnum sensitiveTypeEnum : values()) {
                if (code.equals(sensitiveTypeEnum.getCode()))
                    return sensitiveTypeEnum;
            }
        return null;
    }
}
