package com.plucial.gae.global.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	/**
     * パスワード暗号化
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static String getCipherPassword(long userId, String password) throws NoSuchAlgorithmException {
        StringBuilder buff = new StringBuilder();
        if (password != null && !password.isEmpty()) {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(String.valueOf(userId).getBytes());
            md.update(password.getBytes());
            byte[] digest = md.digest();
            for (byte d : digest) {
                buff.append((int)d&0xFF);
            }
        }
        return buff.toString();
    }
	
	/**
     * 文字列の改行コードを除去する
     * @param data
     * @return
     */
    public static String clearTextIndention(String content) {
        if(content == null || content.trim().length() <= 0) return null;
        
        String newContent = content.replaceAll("\\r\\n|\\n\\r|\\r|\\n", "");
        
        return newContent;
    }
	
	/**
     * 文字列を適切なHTMLに変換
     * @param data
     * @return
     */
    public static String changeIndentionToHtml(String content) {
        if(content == null || content.trim().length() <= 0) return null;
        
        String newContent = content.replaceAll("\\r\\n|\\n\\r|\\r|\\n", "<br />");
        
        return newContent;
    }
    
    /**
     * 文字列を適切なHTMLに変換
     * @param data
     * @return
     */
    public static String changeBrToTextIndention(String content) {
        if(content == null || content.trim().length() <= 0) return null;
        
        String newContent = content.replaceAll("<br />|<br>|<BR />|<BR>", "\n");
        
        return newContent;
    }
	
	/**
     * 改行の統一(HTMLの改行、改行コードをすべて\nに統一する)
     * @param content
     * @return
     */
    public static String unityIndention(String content) {
        
        if(content == null || content.trim().length() <= 0) return null;
        
        // 改行
        String newContent = content.replaceAll("\\r\\n|\\n\\r|\\r", "\n");
        
        return newContent;
    }

}
