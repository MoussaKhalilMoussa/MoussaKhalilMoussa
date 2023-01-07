package com.example.demo.Repositories;

import com.example.demo.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>{

    @Query("SELECT o FROM Order o WHERE o.createDate>?1")
    List<Order> findOrderByDateAfterLike(LocalDate date);

}
