package com.plucial.gae.global.exception;

public class NoContentsException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "このコンテンツは存在しません。";
    
    /**
     * コンストラクタ
     */
    public NoContentsException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public NoContentsException(String message) {
        super(message);
    }

}
