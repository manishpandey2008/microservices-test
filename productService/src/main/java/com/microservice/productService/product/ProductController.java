package com.microservice.productService.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;
    private final Logger logger= LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("save")
    public ProductModel saveUser(@RequestBody ProductModel userModel){
        return productService.saveUser(userModel);
    }

    @GetMapping
    public List<ProductModel> getAllUsers(){
        return productService.getListOfAllUser();
    }

    @GetMapping("/:id")
    public ProductModel getUserById(@PathVariable("id") Long id){
        return productService.getByUserId(id);
    }

}
