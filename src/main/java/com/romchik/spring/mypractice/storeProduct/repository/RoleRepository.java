package com.romchik.spring.mypractice.storeProduct.repository;

import com.romchik.spring.mypractice.storeProduct.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);
}
