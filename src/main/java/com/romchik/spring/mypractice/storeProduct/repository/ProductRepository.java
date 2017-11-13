package com.romchik.spring.mypractice.storeProduct.repository;

import com.romchik.spring.mypractice.storeProduct.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
