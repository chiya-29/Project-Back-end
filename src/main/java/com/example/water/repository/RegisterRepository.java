package com.example.water.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.water.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findByCustomerName(String customerName);
}
