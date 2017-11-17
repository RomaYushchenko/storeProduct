package com.romchik.spring.mypractice.storeProduct.controller;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import com.romchik.spring.mypractice.storeProduct.service.api.EmployeeService;
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
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> findAllEmployee(Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN")) {
            List<Employee> employees = employeeService.findAllEmployee();
            return employees.stream()
                    .sorted(Comparator.comparing(Employee::getId))
                    .collect(Collectors.toList());
        }

        return null;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return employeeService.addEmployee(employee);

        return null;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return employeeService.updateEmployee(employee);

        return null;
    }

    @RequestMapping(value = "/employee/{idEmployee}", method = RequestMethod.DELETE)
    public boolean removeEmployee(@PathVariable("idEmployee") int idEmployee, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if (employeeRole != null && employeeRole.equals("ADMIN"))
            return employeeService.removeEmployee(idEmployee);

        return false;
    }

    @RequestMapping(value = "/employee/{idEmployee}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable("idEmployee") int idEmployee, Authentication authentication) {
        String employeeRole = employeeService.findEmployeeByRoleEmployee(authentication.getName());

        if(employeeRole != null && employeeRole.equals("ADMIN") || employeeRole.equals("USER"))
            return employeeService.findEmployee(idEmployee);

        return null;
    }
}
