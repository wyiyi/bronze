package com.amber.demo.blog;

import com.amber.demo.util.CodeUtil;

public class WaterMark {

    /**
     * 添加水印数据
     * @param src 源文件字符串
     * @param watermark 水印
     * @param pos 参数
     * @return 添加水印后的字符串
     */
    public String addWatermark(String src, String watermark, int pos) {
        if(pos == CodeUtil.WATERMARK_POS_HEAD) {
            return watermark + src;
        } else if(pos == CodeUtil.WATERMARK_POS_TAIL) {
            return src + watermark;
        }
        return src;
    }

    /**
     * 提取水印数据
     * @param input 添加水印文本
     * @param pos 参数
     * @return 水印数据
     */
    public String extractWatermark(String input, int pos) {
        String watermark = "";
        if(pos == CodeUtil.WATERMARK_POS_HEAD) {
            for(int i = 0; i < input.length(); i++) {
                if(input.charAt(i) != '\u200b' && input.charAt(i) != '\u200c' && input.charAt(i) != '\u200d' && input.charAt(i) != '\ufeff') {
                    watermark = input.substring(0, i);
                    break;
                }
            }
        } else if(pos == CodeUtil.WATERMARK_POS_TAIL) {
            for(int i = input.length() - 1; i >= 0; i--) {
                if(input.charAt(i) != '\u200b' && input.charAt(i) != '\u200c' && input.charAt(i) != '\u200d' && input.charAt(i) != '\ufeff') {
                    watermark = input.substring(i + 1);
                    break;
                }
            }
        }
        return watermark;
    }
}
