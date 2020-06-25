package com.example.roomManager.utils;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Couldn't find resource for given id");
    }
}
