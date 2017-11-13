package com.romchik.spring.mypractice.storeProduct.repository;

import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
