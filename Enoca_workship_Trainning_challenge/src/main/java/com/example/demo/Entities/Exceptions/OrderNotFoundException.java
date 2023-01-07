package com.example.demo.Entities.Exceptions;

import java.text.MessageFormat;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super(MessageFormat.format("could not find Order with id {0} :", id));
    }
}
