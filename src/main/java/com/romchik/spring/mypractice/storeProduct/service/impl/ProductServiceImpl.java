package com.romchik.spring.mypractice.storeProduct.service.impl;

import com.romchik.spring.mypractice.storeProduct.model.entity.Product;
import com.romchik.spring.mypractice.storeProduct.repository.ProductRepository;
import com.romchik.spring.mypractice.storeProduct.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roman.yushchenko
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        if (product != null && productRepository.findOne(product.getId()) == null)
            return productRepository.save(product);

        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        Product updateProduct = productRepository.findOne(product.getId());

        if (updateProduct != null) {
            updateProduct.setId(product.getId());
            updateProduct.setName(product.getName());
            updateProduct.setSize(product.getSize());
            updateProduct.setWeight(product.getWeight());

            return productRepository.save(updateProduct);
        }

        return null;
    }

    @Override
    public boolean removeProduct(int idProduct) {
        Product product = productRepository.findOne(idProduct);

        if (product != null) {
            productRepository.delete(idProduct);
            return true;
        }

        return false;
    }

    @Override
    public Product findProduct(int idProduct) {
        Product product = productRepository.findOne(idProduct);

        if (product != null)
            return product;

        return null;
    }
}
