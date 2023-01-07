package com.example.demo.Entities.Dto;

import com.example.demo.Entities.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class CustomerDto {

    private Long customerId;
    private String customerName;
    private int age;
    private List<OrderDto> orders = new ArrayList<>();

    public static CustomerDto from(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomer_id());

        customerDto.setCustomerName(customer.getCustom_name());
        customerDto.setAge(customer.getCustom_age());
        customerDto.setOrders(customer.getOrders().stream().map(OrderDto::from).collect(Collectors.toList()));
        return customerDto;
    };

}