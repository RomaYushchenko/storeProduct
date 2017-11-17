package com.romchik.spring.mypractice.storeProduct.repository;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT role.name FROM employee INNER JOIN employee_role ON(employee.id = employee_role.employee_id) INNER JOIN role ON(employee_role.role_id = role.id) WHERE employee.login = ?1", nativeQuery = true)
    String findByRoleEmployee(String login);
    @Query(value = "SELECT id FROM employee WHERE login = ?1", nativeQuery = true)
    String findByIdEmployee(String login);
}
