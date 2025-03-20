package com.microservice.orderService.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/order")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger= LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("save")
    public OrderModel saveUser(@RequestBody OrderModel userModel){
        return orderService.saveUser(userModel);
    }

    @GetMapping
    public List<OrderModel> getAllUsers(){
        return orderService.getListOfAllUser();
    }

    @GetMapping("/:id")
    public OrderModel getUserById(@PathVariable("id") Long id){
        return orderService.getByUserId(id);
    }

}
