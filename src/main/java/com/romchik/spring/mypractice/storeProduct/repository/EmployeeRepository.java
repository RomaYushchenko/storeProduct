package com.romchik.spring.mypractice.storeProduct.repository;

import com.romchik.spring.mypractice.storeProduct.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
