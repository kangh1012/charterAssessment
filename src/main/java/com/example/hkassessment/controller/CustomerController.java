package com.example.hkassessment.controller;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Map<String, String> custInfo) {
        Customer customer = customerService.createNewCustomer(custInfo.get("name"));

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> customers = customerService.findAllCustomers();

        if(customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteCustomer(long customerId) {
        customerService.delete(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
