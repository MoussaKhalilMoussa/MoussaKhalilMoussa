package com.example.demo.Repositories;

import com.example.demo.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.custom_name LIKE %?1%")
    List<Customer> findByCustomerNameLike(String custom_name);

    @Query("SELECT c FROM Customer c WHERE c.orders IS EMPTY ")
    List<Customer> findAllCustomerNotHavingOrders();

}
