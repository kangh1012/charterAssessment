package com.example.hkassessment.service;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TransactionService {
    Transaction createNewTransaction(Double amount, Customer customer, int month);

    List<Transaction> findByCustomer(Customer customer);

    Map<Integer, Integer> calculatePointsPerMonth(List<Transaction> transactions);

    Integer calculateTotalPoints(List<Transaction> transactions);

    void delete(long transactionId);
}
