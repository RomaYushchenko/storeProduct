package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;
import com.romchik.spring.mypractice.storeProduct.service.api.SaleService;
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
public class SaleController {

    @Autowired
    SaleService saleService;

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public List<Sale> findAllSale(){
        List<Sale> sales = saleService.findAllSale();

        return sales.stream()
                .sorted(Comparator.comparing(Sale::getSales))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/sale", method = RequestMethod.POST)
    public Sale addSale(@RequestBody Sale sale){
        return  saleService.addSale(sale);
    }

    @RequestMapping(value = "/sale", method = RequestMethod.PUT)
    public Sale updateSale(@RequestBody Sale sale){
        return saleService.updateSale(sale);
    }

    @RequestMapping(value = "/sale/{idSale}", method = RequestMethod.DELETE)
    public boolean removeSale(@PathVariable("idSale") int idSale){
        return saleService.removeSale(idSale);
    }

    @RequestMapping(value = "/sale/{idSale}", method = RequestMethod.GET)
    public Sale findSale(@PathVariable("idSale") int idSale){
        return saleService.findSale(idSale);
    }
}
