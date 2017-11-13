package com.romchik.spring.mypractice.storeProduct.service.api;

import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAllSale();

    Sale addSale(Sale sale);

    Sale updateSale(Sale sale);

    boolean removeSale(int idSale);

    Sale findSale(int idSale);
}
