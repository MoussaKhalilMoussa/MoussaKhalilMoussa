package com.example.demo.Entities.Exceptions;

import java.text.MessageFormat;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super(MessageFormat.format("Could not found Customer with id : {0}",id));
    }
}
