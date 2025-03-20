package com.microservice.orderService.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final Logger logger= LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public OrderModel saveUser(OrderModel orderModel){
        logger.info("Entered into UserService of saveUser method: {}",orderModel.getId());
        return orderRepo.save(orderModel);
    }

    public List<OrderModel> getListOfAllUser(){
        return orderRepo.findAll();
    }

    public OrderModel getByUserId(Long id){
        return orderRepo.findById(id).orElse(null);
    }
}
