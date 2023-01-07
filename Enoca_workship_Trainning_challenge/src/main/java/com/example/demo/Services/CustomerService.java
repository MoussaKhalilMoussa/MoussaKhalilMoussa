package com.example.demo.Services;

import com.example.demo.Entities.Customer;
import com.example.demo.Entities.Exceptions.CustomerNotFoundException;
import com.example.demo.Entities.Exceptions.OrderIsAlreadyAssignedException;
import com.example.demo.Entities.Order;
import com.example.demo.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final OrderService orderService;

    @Autowired
    public CustomerService(CustomerRepo customerRepo,OrderService orderService) {
        this.customerRepo = customerRepo;
        this.orderService = orderService;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }
    public Customer getCustomerById(Long id){
        return customerRepo.findById(id).orElseThrow(()->
                new CustomerNotFoundException(id));
    }

    public List<Customer> getAllCustomers() {
        return StreamSupport.stream(customerRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepo.delete(customer);
        return customer;
    }

    @Transactional
    public Customer addOrderToCustomer(Long customId , Long orderId){
        Customer customer = getCustomerById(customId);
        Order order = orderService.getOrderById(orderId);
        if(Objects.nonNull(order.getCustomer())){
            throw new OrderIsAlreadyAssignedException(orderId,order.getCustomer().getCustomer_id());
        }
        customer.addOrder(order);
        return  customer;
    }

    @Transactional
    public Customer removeOrderFromCustomer(Long customerId ,Long orderId ){
        Customer customer = getCustomerById(customerId);
        Order order = orderService.getOrderById(orderId);
        customer.removeOrder(order);
        return customer;
    }

    @Transactional
    public Customer editCustomer(Long id, Customer customer) {
        Customer customerToEdit = getCustomerById(id);
        customerToEdit.setCustom_name(customer.getCustom_name());
        customerToEdit.setCustom_age(customer.getCustom_age());
        customerToEdit.setOrders(customer.getOrders());
        customerToEdit.setOrders(customer.getOrders());
        return customerToEdit;
    }

    @Transactional
    public List<Customer> findByCustomerNameLike(String custom_name){
        return customerRepo.findByCustomerNameLike(custom_name);
    }

    @Transactional
    public List<Customer> findAllCustomerNotHavingOrders(){
        return customerRepo.findAllCustomerNotHavingOrders();
    }

}
