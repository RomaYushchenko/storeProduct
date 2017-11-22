package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import com.romchik.spring.mypractice.storeProduct.model.entity.Sale;
import com.romchik.spring.mypractice.storeProduct.service.api.EmployeeService;
import com.romchik.spring.mypractice.storeProduct.service.api.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    public static final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    SaleService saleService;
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public List<Sale> findAllSale(Authentication authentication){
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN")){
                List<Sale> sales = saleService.findAllSale();
                return sales.stream()
                        .sorted(Comparator.comparing(Sale::getSales))
                        .collect(Collectors.toList());
        }

        return null;
    }

    @RequestMapping(value = "/sale", method = RequestMethod.POST)
    public Sale addSale(@RequestBody Sale sale, Authentication authentication){
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN"))
            return  saleService.addSale(sale);

        return null;
    }

    @RequestMapping(value = "/sale", method = RequestMethod.PUT)
    public Sale updateSale(@RequestBody Sale sale, Authentication authentication){
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
              return saleService.updateSale(sale);

        return null;
    }

    @RequestMapping(value = "/sale/{idSale}", method = RequestMethod.DELETE)
    public boolean removeSale(@PathVariable("idSale") int idSale, Authentication authentication){
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN"))
            return saleService.removeSale(idSale);
        else {
            if (employeeRole.equals("USER")) {
                Sale sale = saleService.findSale(idSale);
                Employee employee = employeeService.findEmployee(Integer.parseInt(employeeService.findEmployeeByIdEmployee(authentication.getName())));
                List<Sale> sales = saleService.findAllSale();

                for (Sale counterSale : sales) {
                    if (saleService.findSale(counterSale.getId()).getId() == sale.getId()
                            && saleService.findSale(counterSale.getId()).getEmployee().getId() == employee.getId()) {
                        return saleService.removeSale(idSale);
                    }
                }
            }
        }

        return false;
    }

    @RequestMapping(value = "/sale/{idSale}", method = RequestMethod.GET)
    public Sale findSale(@PathVariable("idSale") int idSale, Authentication authentication){
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return saleService.findSale(idSale);

        return null;
    }
}
