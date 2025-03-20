package com.microservice.productService.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final Logger logger= LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductModel saveUser(ProductModel productModel){
        logger.info("Entered into UserService of saveUser method: {}", productModel.getName());
        return productRepo.save(productModel);
    }

    public List<ProductModel> getListOfAllUser(){
        return productRepo.findAll();
    }

    public ProductModel getByUserId(Long id){
        return productRepo.findById(id).orElse(null);
    }


}
