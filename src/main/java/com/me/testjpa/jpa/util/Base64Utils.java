package com.me.testjpa.jpa.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    private static String  DEFAULT_CHARSET = "UTF-8";

    /**
     *  编码（加密）
     * @param str 需要编码的字符串
     * @param charset 编码字符集
     * @return 编码后字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String encode(String str,String charset) throws UnsupportedEncodingException {
        return ENCODER.encodeToString(str.getBytes(charset));
    }

    /**
     *  编码（加密）
     * @param str 需要编码的字符串
     * @return 编码后字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String encode(String str)throws UnsupportedEncodingException{
        return encode(str,DEFAULT_CHARSET);
    }

    /**
     *  解码（解密）
     * @param str 需要解码的字符串
     * @param charset 解码字符集
     * @return 解码后字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String decode(String str,String charset) throws UnsupportedEncodingException {
        return new String(DECODER.decode(str),charset);
    }
    /**
     *  解码（解密）
     * @param str 需要解码的字符串
     * @return 解码后字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        return new String(DECODER.decode(str),DEFAULT_CHARSET);
    }
}
