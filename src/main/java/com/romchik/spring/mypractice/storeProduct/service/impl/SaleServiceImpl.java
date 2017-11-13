package com.romchik.spring.mypractice.storeProduct.service.impl;

import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;
import com.romchik.spring.mypractice.storeProduct.repository.SaleRepository;
import com.romchik.spring.mypractice.storeProduct.service.api.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author roman.yushchenko
 * @version 1.0
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> findAllSale() {
        return saleRepository.findAll();
    }

    @Override
    public Sale addSale(Sale sale) {
        if(sale != null && saleRepository.findOne(sale.getId()) == null){
            return saleRepository.save(sale);
        }

        return null;
    }

    @Override
    public Sale updateSale(Sale sale) {
        Sale updateSale = saleRepository.findOne(sale.getId());

        if(updateSale != null){
            updateSale.setId(sale.getId());
            updateSale.setEmployee(sale.getEmployee());
            updateSale.setPrice(sale.getPrice());
            updateSale.setProduct(sale.getProduct());
            updateSale.setSales(sale.getSales());

            return saleRepository.save(updateSale);
        }

        return null;
    }

    @Override
    public boolean removeSale(int idSale) {
        Sale sale = saleRepository.findOne(idSale);

        if (sale != null) {
            saleRepository.delete(idSale);
            return true;
        }

        return false;
    }

    @Override
    public Sale findSale(int idSale) {
        Sale sale = saleRepository.findOne(idSale);

        if (sale != null)
            return sale;

        return null;
    }
}
