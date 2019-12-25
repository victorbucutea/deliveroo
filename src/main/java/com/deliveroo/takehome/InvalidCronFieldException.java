package com.deliveroo.takehome;

public class InvalidCronFieldException extends Throwable {
    public InvalidCronFieldException(String message) {
        super(message);
    }
}
