package com.eighth.airrent.proxy.exception;

import java.io.IOException;

/**
 * Created by dam on 2014/6/30.
 */
public class RemoteInvokeException extends IOException {

    public RemoteInvokeException(){

    }

    public RemoteInvokeException(String message){
        super(message);
    }
}
