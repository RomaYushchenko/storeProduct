package com.romchik.spring.mypractice.storeProduct.service.impl;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import com.romchik.spring.mypractice.storeProduct.model.entity.Role;
import com.romchik.spring.mypractice.storeProduct.repository.EmployeeRepository;
import com.romchik.spring.mypractice.storeProduct.repository.RoleRepository;
import com.romchik.spring.mypractice.storeProduct.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author roman.yushchenko
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (employee != null && employeeRepository.findOne(employee.getId()) == null){
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
            Role employeeRole = roleRepository.findByRole("USER");
            employee.setRoles(new HashSet<>(Arrays.asList(employeeRole)));
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updateEmployee = employeeRepository.findOne(employee.getId());

        if (updateEmployee != null) {
            updateEmployee.setId(employee.getId());
            updateEmployee.setLogin(employee.getLogin());
            updateEmployee.setPassword(employee.getPassword());
            updateEmployee.setEmail(employee.getEmail());

            return employeeRepository.save(updateEmployee);
        }

        return null;
    }

    @Override
    public boolean removeEmployee(int idEmployee) {
        Employee employee = employeeRepository.findOne(idEmployee);

        if (employee != null) {
            employeeRepository.delete(idEmployee);
            return true;
        }

        return false;
    }

    @Override
    public Employee findEmployee(int idEmployee) {
        Employee employee = employeeRepository.findOne(idEmployee);

        if (employee != null)
            return employee;

        return null;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
