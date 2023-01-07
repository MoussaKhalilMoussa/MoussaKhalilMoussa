package com.example.demo.Entities.Dto;

import com.example.demo.Entities.Order;
import lombok.Data;
import java.time.LocalDate;
@Data
public class OrderDto {
    private Long orderId;
    private Long totalPrice;
    private LocalDate createDate;

    public static OrderDto from(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setCreateDate(order.getCreateDate());
        return orderDto;
    }
}
