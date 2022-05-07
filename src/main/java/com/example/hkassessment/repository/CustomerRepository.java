package com.example.hkassessment.repository;

import com.example.hkassessment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    void deleteByCustomerId(Long id);

    Optional<Customer> findByCustomerId(long custId);
}
