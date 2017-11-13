package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import com.romchik.spring.mypractice.storeProduct.service.api.EmployeeService;
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
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> findAllEmployee(){
        List<Employee> employees = employeeService.findAllEmployee();

        return employees.stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/employee/{idEmployee}", method = RequestMethod.DELETE)
    public boolean removeEmployee(@PathVariable("idEmployee") int idEmployee){
        return employeeService.removeEmployee(idEmployee);
    }

    @RequestMapping(value = "/employee/{idEmployee}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable("idEmployee") int idEmployee){
        return employeeService.findEmployee(idEmployee);
    }
}
