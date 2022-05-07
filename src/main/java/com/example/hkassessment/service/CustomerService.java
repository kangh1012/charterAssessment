package com.example.hkassessment.service;

import com.example.hkassessment.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    Customer createNewCustomer(String name);

    Optional<Customer> findById(long id);

    List<Customer> findAllCustomers();

    void delete(long customerId);
}
