package com.plucial.gae.global.exception;

public class IsNotSupportedLangException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "This language is not supported.";
    
    /**
     * コンストラクタ
     */
    public IsNotSupportedLangException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public IsNotSupportedLangException(String message) {
        super(message);
    }

}
