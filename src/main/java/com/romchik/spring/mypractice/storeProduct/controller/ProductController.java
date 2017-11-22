package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Product;
import com.romchik.spring.mypractice.storeProduct.service.api.EmployeeService;
import com.romchik.spring.mypractice.storeProduct.service.api.ProductService;
import com.romchik.spring.mypractice.storeProduct.service.api.SaleService;
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
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SaleService saleService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> findAllProducts(Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER")) {
            List<Product> products = productService.findAllProduct();
            return products.stream()
                    .sorted(Comparator.comparing(Product::getId))
                    .collect(Collectors.toList());
        }

        return null;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return productService.addProduct(product);

        return null;
    }

    @RequestMapping(value = "/product", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN"))
            return productService.updateProduct(product);

        return null;
    }

    @RequestMapping(value = "/product/{idProduct}", method = RequestMethod.DELETE)
    public boolean removeProduct(@PathVariable("idProduct") int idProduct, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN"))
            return productService.removeProduct(idProduct);

        return false;
    }

    @RequestMapping(value = "/product/{idProduct}", method = RequestMethod.GET)
    public Product findProduct(@PathVariable("idProduct") int idProduct, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return productService.findProduct(idProduct);

        return null;
    }
}
