package com.example.demo.Entities;

import com.example.demo.Entities.Dto.CustomerDto;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "customer_table")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence")
    private Long customer_id;
    private String custom_name;
    private int custom_age;

    @OneToMany(cascade = CascadeType.ALL ,targetEntity = Order.class )
    @JoinColumn(name = "customerId" , referencedColumnName = "customer_id")
    private List<Order> orders = new ArrayList<>();


    public void addOrder(Order order){
        orders.add(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public static Customer from(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCustomer_id(customerDto.getCustomerId());
        customer.setCustom_age(customerDto.getAge());
        customer.setCustom_name(customerDto.getCustomerName());
        return customer;
    }


}
