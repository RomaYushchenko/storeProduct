package com.romchik.spring.mypractice.storeProduct.service.api;

import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;

import java.util.List;

/**
 * @author roman.yushchenko
 */
public interface SaleService {

    List<Sale> findAllSale();

    Sale addSale(Sale sale);

    Sale update(Sale sale);

    boolean removeSale(int idSale);

    Sale findSale(int idSale);
}
