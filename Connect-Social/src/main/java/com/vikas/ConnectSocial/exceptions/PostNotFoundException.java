package com.vikas.ConnectSocial.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String msg){
        super(msg);
    }
}
