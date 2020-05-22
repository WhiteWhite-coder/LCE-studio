package com.miaojie.utils;

import sun.security.provider.MD5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 吴淼杰
 * 老天保佑，佛祖保佑，别出bug！
 */
public class Md5Utils {
    public static  String md5(String str){
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 通过使用 update 方法处理数据
            messageDigest.update(str.getBytes("utf-8"));
            // 获得密文
            byte[] digest = messageDigest.digest();
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            BigInteger bigInteger = new BigInteger(1,digest);
            String secret = bigInteger.toString(16);
            // 返回
            return secret;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
