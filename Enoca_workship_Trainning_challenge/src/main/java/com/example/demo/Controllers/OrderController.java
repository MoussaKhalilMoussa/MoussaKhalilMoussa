package com.example.demo.Controllers;

import com.example.demo.Entities.Dto.OrderDto;
import com.example.demo.Entities.Order;
import com.example.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/giveDate/{date}")
    public ResponseEntity<List<OrderDto>> findOrderByDateAfterLike(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date){
        List<Order> orders = orderService.findOrderByDateAfterLike(date);
        List<OrderDto> orderDto = orders.stream().map(OrderDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder (@RequestBody final OrderDto orderDto){
        Order order = orderService.addOrder(Order.from(orderDto));
        return new ResponseEntity<>(OrderDto.from(order), HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<List<OrderDto>>getOrders(){
        List<Order> orders = orderService.getOrders();
        List<OrderDto> orderDto = orders.stream().map(OrderDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable final Long id){
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(OrderDto.from(order),HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable final Long id){
        Order order = orderService.deleteOrder(id);
        return new ResponseEntity<>(OrderDto.from(order),HttpStatus.OK);
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<OrderDto> editOrder(@PathVariable final Long id ,@RequestBody final OrderDto orderDto){
        Order editOrder = orderService.editOrder(id,Order.from(orderDto));
        return new ResponseEntity<>(OrderDto.from(editOrder),HttpStatus.OK);
    }
}
