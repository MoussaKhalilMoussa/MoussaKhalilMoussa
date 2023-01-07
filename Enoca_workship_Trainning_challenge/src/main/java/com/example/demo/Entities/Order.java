package com.example.demo.Entities;
import com.example.demo.Entities.Dto.OrderDto;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@DateTimeFormat(pattern = "dd-MM-yyyy at")
    private Long totalPrice;
    private LocalDate createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Order() {
    }

    public static Order from(OrderDto orderDto){
        Order order = new Order();
        order.setCreateDate(orderDto.getCreateDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }




}
