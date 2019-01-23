package com.leosunrise.cryptography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: Leo Dominic Xu
 * @Date: 2019/1/24 02:12
 * @Description: SHA256算法
 */
public class SHA256Utils {
    protected static Logger logger = LoggerFactory.getLogger(SHA256Utils.class);

    /**
     * 字节数组转换为sha256哈希码
     * @param bytes 字节数组
     * @return 64位哈希码
     */
    public static String sha256Hex(byte[] bytes) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.warn("SHA256加密异常!", e);
        }
        return encodeStr;
    }

    /**
     * 　　* 将byte转为16进制
     * 　　* @param bytes
     * 　　* @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString().toLowerCase();
    }
    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byte2Hex(bytes);
        } catch (Exception e) {
            logger.warn("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }
}
