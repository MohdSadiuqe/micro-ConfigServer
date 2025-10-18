package com.usersService.usersService.Exception;

import org.aspectj.bridge.Message;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource Not Found Exception!!");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
