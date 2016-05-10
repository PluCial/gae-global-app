package com.plucial.gae.global.exception;

/**
 * 翻訳エラー
 * @author takahara
 *
 */
public class TransException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "翻訳に失敗しました。しばらく時間立ってから再度実行してください。";
    
    /**
     * コンストラクタ
     */
    public TransException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public TransException(Throwable cause) {
        super(message, cause);
    }
    
    /**
     * コンストラクタ
     */
    public TransException(String message) {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public TransException(String message, Throwable cause) {
        super(message, cause);
    }

}
