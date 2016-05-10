package com.plucial.gae.global.exception;

public class ObjectNotExistException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private static final String message = "対象のオブジェクトは存在しません。";
    
    /**
     * コンストラクタ
     */
    public ObjectNotExistException() {
        super(message);
    }
    
    /**
     * コンストラクタ
     */
    public ObjectNotExistException(String message) {
        super(message);
    }

}
