package com.example.demo.Entities.Exceptions;

import java.text.MessageFormat;

public class OrderIsAlreadyAssignedException extends RuntimeException{

    public OrderIsAlreadyAssignedException(final Long orderId , final Long customerId){
        super(MessageFormat.format("order : {0} is already assigned to customer : {1}",orderId,customerId));
    }
}
