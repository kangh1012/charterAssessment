package com.example.hkassessment.controller;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.entity.Transaction;
import com.example.hkassessment.exception.EntityNotFoundException;
import com.example.hkassessment.service.CustomerService;
import com.example.hkassessment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Map<String, ?> map) {
        Customer customer = customerService.findById(((Number) map.get("custId")).longValue())
                .orElseThrow(() -> new EntityNotFoundException("Customer notfound with id=" + map.get("custId")));

        Transaction transaction = transactionService.createNewTransaction(
                (Double) map.get("amount"), customer, (int) map.get("month"));

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/findByCustomer/{custId}")
    public ResponseEntity<List<Transaction>> findByCustomer(@PathVariable("custId") long custId) {
        Customer customer = customerService.findById(custId).
                orElseThrow(() -> new EntityNotFoundException("Customer not found with id=" + custId));
        List<Transaction> transactions = transactionService.findByCustomer(customer);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/pointsPerMonth")
    public ResponseEntity<Map<String, Map<Integer, Integer>>> calculatePointsPerMonth() {
        Map<String, Map<Integer, Integer>> res = new HashMap<>();
        List<Customer> customers = customerService.findAllCustomers();
        for(Customer customer : customers) {
            Map<Integer, Integer> map = transactionService.calculatePointsPerMonth(transactionService.findByCustomer(customer));
            res.put(customer.getCustomerName(), map);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/totalPoints")
    public ResponseEntity<Map<String, Integer>> calculateTotalPoints() {
        Map<String, Integer> res = new HashMap<>();
        List<Customer> customers = customerService.findAllCustomers();
        for(Customer customer: customers) {
            int totalPoints = transactionService.calculateTotalPoints(transactionService.findByCustomer(customer));
            res.put(customer.getCustomerName(), totalPoints);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
