package com.hh.wx.xcx.commons;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Base64Code {
	final static Base64 base64 = new Base64();
	public static String encodeToString(String text){
		try {
			byte[] textByte = text.getBytes("UTF-8");
			return base64.encodeToString(textByte);
		} catch (UnsupportedEncodingException e) {
			log.error("编码异常",e);
		}
		return null;
	}
	
	public static String decode(String encodedText){
		try {
			return new String(base64.decode(encodedText), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("解码异常",e);
		}
		return null;
	}
	
	public static String getToken(String key){
		
		return encodeToString(System.currentTimeMillis()+key);
	}
}
