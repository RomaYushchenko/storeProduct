package com.romchik.spring.mypractice.storeProduct.service.api;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    boolean removeEmployee(int idEmployee);

    Employee findEmployee(int idEmployee);
}
