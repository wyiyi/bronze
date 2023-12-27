package com.amber.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class CryptoUtil {

    public static final String ALGORITHM_SHA = "SHA";

    public static final String ALGORITHM_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String ALGORITHM_HMAC_MD5 = "HmacMD5";


    private final static String ALGORITHM_AES = "AES";


    /**
     * 加密
     *
     * @param key
     * @param content
     * @return base64编码后字符串
     */
    public static String encryptAesBase64(String key, String content) {

        try {
            return encryptBase64(encryptAes(key.getBytes(), content.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            log.error("加密错误已返回原值", e);
            return content;
        }

    }

    /**
     * 加密
     *
     * @param content
     * @return base64编码后字符串
     */
    public static String encryptMd5Base64(String content) {

        try {
            return encryptBase64(encryptMd5(content.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            log.error("加密错误已返回原值", e);
            return content;
        }

    }

    /**
     * 解密
     *
     * @param key
     * @param content
     * @return
     */
    public static String decryptAesBase64(String key, String content) {

        try {
            return new String(decryptAes(key, decryptBase64(content)));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException e) {
            log.error("解密错误已返回原值", e);
            return content;
        }
    }

    /**
     * BASE64解密
     *
     * @param content
     * @return
     */
    public static byte[] decryptBase64(String content) {
        return Base64.getDecoder().decode(content);
    }

    /**
     * BASE64加密
     *
     * @param content
     * @return
     */
    public static String encryptBase64(byte[] content) {
        return Base64.getEncoder().encodeToString(content);
    }

    /**
     * MD5加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] encryptMd5(byte[] content) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance(ALGORITHM_MD5);
        md5.update(content);

        return md5.digest();

    }

    /**
     * SHA加密
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] content) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(ALGORITHM_SHA);
        sha.update(content);

        return sha.digest();

    }

    /**
     * HMAC加密
     *
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(String key, byte[] content) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBase64(key), ALGORITHM_HMAC_MD5);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(content);

    }

    /**
     * @param key     密钥
     * @param content 需要加密的字符串
     * @return 密文字节数组
     */
    private static byte[] encryptAes(byte[] key, byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] rawKey = genAesKey(key);

        SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(content);
    }

    /**
     * @param content 密文字节数组
     * @param key     密钥
     * @return
     */
    private static byte[] decryptAes(String key, byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] rawKey = genAesKey(key.getBytes());

        SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(content);
    }

    /**
     * 获取密钥数据
     *
     * @param seed 种子数据
     * @return 密钥数据
     */
    private static byte[] genAesKey(byte[] seed) {
        byte[] rawKey = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM_AES);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            // AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            rawKey = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return rawKey;
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_HMAC_MD5);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBase64(secretKey.getEncoded());
    }

}
