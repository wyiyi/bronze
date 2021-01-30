package com.amber.demo.util;

public class DeEncode {
    public String encode(String input) {
        String binary = strToBinary(input);
        return binaryToZeroWidth(binary);
    }

    public String decode(String input) {
        String binary = zeroWidthToBinary(input);
        return binaryToStr(binary);
    }

    private String strToBinary(String input) {
        char[] strChar = input.toCharArray();
        String result = "";
        String tmp = "";
        for (int i = 0; i < strChar.length; i++) {
            tmp = Integer.toBinaryString(strChar[i]);

            while (tmp.length() < 16) {
                tmp = "0" + tmp;
            }
            result += tmp;
            result += " ";
        }
        return result.trim();
    }

    private String binaryToZeroWidth(String input) {
        String[] stringArray = input.split(" ");
        String result = "";
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length(); j++) {
                //数字转换
                int num = Integer.parseInt(stringArray[i].charAt(j) + "");
                if (num == 1) {
                    result += '\u200b'; // \u200b 零宽度字符（zero-width space）
                } else if (num == 0) {
                    result += '\u200c'; // \u200c 零宽度断字符（zero-width non-joiner）
                } else {
                    result += '\u200d'; // \u200d 零宽度连字符 (zero-width joiner)
                }
                result += '\ufeff'; // \ufeff 零宽度非断空格符 (zero width no-break space)
            }
        }
        return result;
    }

    private String zeroWidthToBinary(String input) {
        String result = "";
        String[] binaryStr = input.split("\ufeff");
        for (int i = 0; i < binaryStr.length; i++) {
            if (binaryStr[i].equals("\u200B")) {
                result += "1";
            } else if (binaryStr[i].equals("\u200C")) {
                result += "0";
            }
            if ((i + 1) % 16 == 0) {
                result += " ";
            }
        }
        return result;
    }

    private String binaryToStr(String input) {
        String[] tempStr = input.split(" ");
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = binaryToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    private int[] binaryToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    private char binaryToChar(String binStr) {
        int[] temp = binaryToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }
}
