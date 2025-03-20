package com.microservice.orderService.order;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String name;
    private String phone;
}
