package com.romchik.spring.mypractice.storeProduct.service.api;

import com.romchik.spring.mypractice.storeProduct.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    Product addProduct(Product product);

    Product updateProduct(Product product);

    boolean removeProduct(int idProduct);

    Product findProduct(int idProduct);

}
