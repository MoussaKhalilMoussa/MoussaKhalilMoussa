package com.example.demo.Controllers;

import com.example.demo.Entities.Customer;
import com.example.demo.Entities.Dto.CustomerDto;
import com.example.demo.Services.CustomerService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CustomController {
    private final CustomerService customerService;

    @Autowired
    public CustomController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody final CustomerDto customerDto){
        Customer customer = customerService.addCustomer(Customer.from(customerDto));
        return new ResponseEntity<>(CustomerDto.from(customer), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> customerDtos = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<CustomerDto>getCustomerById(@PathVariable final Long id){
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(CustomerDto.from(customer),HttpStatus.OK);
    }

    @GetMapping(value ="/giveAnyString/{custom_name}" )
    public ResponseEntity<List<CustomerDto>> findByCustomerNameLike(@PathVariable String custom_name){
       List<Customer> customers = customerService.findByCustomerNameLike(custom_name);
        List<CustomerDto> customerDtos = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }

    @GetMapping(value = "getAllTheCustomerNotHaving an order")
    public ResponseEntity<List<CustomerDto>>findAllCustomerNotHavingOrders(){
        List<Customer> customers = customerService.findAllCustomerNotHavingOrders();
        List<CustomerDto> customerDtos = customers.stream().map(CustomerDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }


    @DeleteMapping(value="{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable final Long id){

        Customer customer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(CustomerDto.from(customer),HttpStatus.OK);
    }

    @PutMapping(value="{id}")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable final Long id,
                                                  @RequestBody final CustomerDto customerDto){
        Customer customer = customerService.editCustomer(id,Customer.from(customerDto));
        return new ResponseEntity<>(CustomerDto.from(customer),HttpStatus.OK);
    }

    @PostMapping (value = "{customerId}/order/{orderId}/add")
    public ResponseEntity<CustomerDto> addOrderToCustomer(@PathVariable final Long customerId,
                                                           @PathVariable final  Long orderId){
        Customer customer = customerService.addOrderToCustomer(customerId,orderId);
        return  new ResponseEntity<>(CustomerDto.from(customer),HttpStatus.OK);
    }

    @DeleteMapping(value = "{customerId}/order/{orderId}/remove")
    public ResponseEntity<CustomerDto> removeOrderFromCustomer(@PathVariable final Long customerId,
                                                                @PathVariable final  Long orderId){
        Customer customer = customerService.removeOrderFromCustomer(customerId,orderId);
        return  new ResponseEntity<>(CustomerDto.from(customer),HttpStatus.OK);
    }
}
