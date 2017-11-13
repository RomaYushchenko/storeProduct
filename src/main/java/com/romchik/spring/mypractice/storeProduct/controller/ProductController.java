package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Product;
import com.romchik.spring.mypractice.storeProduct.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author roman.yushchenko
 * @version 1.0
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> findAllProducts(){
        List<Product> products = productService.findAllProduct();

        return products.stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/product/{idProduct}", method = RequestMethod.DELETE)
    public boolean removeProduct(@PathVariable("idProduct") int idProduct){
        return productService.removeProduct(idProduct);
    }

    @RequestMapping(value = "/product/{idProduct}", method = RequestMethod.GET)
    public Product findProduct(@PathVariable("idProduct") int idProduct){
        return productService.findProduct(idProduct);
    }
}
