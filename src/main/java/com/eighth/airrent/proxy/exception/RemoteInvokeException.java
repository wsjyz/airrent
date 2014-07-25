package com.eighth.airrent.proxy.exception;

/**
 * Created by dam on 2014/6/30.
 */
public class RemoteInvokeException extends RuntimeException {

    public RemoteInvokeException(){

    }

    public RemoteInvokeException(String message){
        super(message);
    }
}
