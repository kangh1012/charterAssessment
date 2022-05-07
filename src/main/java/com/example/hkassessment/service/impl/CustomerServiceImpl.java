package com.example.hkassessment.service.impl;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.repository.CustomerRepository;
import com.example.hkassessment.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createNewCustomer(String name) {
        Customer customer = new Customer(name);

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return customerRepository.findByCustomerId(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(long customerId) {
        customerRepository.deleteByCustomerId(customerId);
    }
}
