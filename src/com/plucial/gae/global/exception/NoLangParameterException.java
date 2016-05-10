package com.plucial.gae.global.exception;

public class NoLangParameterException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "no language parameter.";
    
    /**
     * コンストラクタ
     */
    public NoLangParameterException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public NoLangParameterException(String message) {
        super(message);
    }

}
