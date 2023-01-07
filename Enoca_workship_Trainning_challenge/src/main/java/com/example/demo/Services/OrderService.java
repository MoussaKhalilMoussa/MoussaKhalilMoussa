package com.example.demo.Services;

import com.example.demo.Entities.Exceptions.OrderNotFoundException;
import com.example.demo.Entities.Order;
import com.example.demo.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order addOrder(Order order){
        return orderRepo.save(order);
    }

    public List<Order> getOrders(){
        return new ArrayList<>(orderRepo.findAll());
    }
    public Order getOrderById(Long id){
        return orderRepo.findById(id).orElseThrow(()->
                new OrderNotFoundException(id));
    }

    @Transactional
    public List<Order>findOrderByDateAfterLike(LocalDate date){
        return orderRepo.findOrderByDateAfterLike(date);
    }
    public Order deleteOrder(Long id){
        Order order = getOrderById(id);
        orderRepo.delete(order);
        return order;
    }

    @Transactional
    public Order editOrder(Long id , Order order) {
        Order orderToEdit = getOrderById(id);
        orderToEdit.setCreateDate(order.getCreateDate());
        orderToEdit.setTotalPrice(order.getTotalPrice());
        return orderToEdit;
    }
}
