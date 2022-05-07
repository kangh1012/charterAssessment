package com.example.hkassessment.service.impl;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.entity.Transaction;
import com.example.hkassessment.repository.TransactionRepository;
import com.example.hkassessment.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createNewTransaction(Double amount, Customer customer, int month) {
        Transaction transaction = new Transaction(amount, customer, month);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByCustomer(Customer customer) {
        return transactionRepository.findByCustomer(customer);
    }

    @Override
    public Map<Integer, Integer> calculatePointsPerMonth(List<Transaction> transactions) {
        Map<Integer, Integer> map = new HashMap<>();

        for(Transaction transaction: transactions) {
            int amount = (int) Math.floor(transaction.getAmount());
            int point = calculatePoint(amount);

            map.put(transaction.getMonth(), map.getOrDefault(transaction.getMonth(), 0) + point);
        }

        return map;
    }

    @Override
    public Integer calculateTotalPoints(List<Transaction> transactions) {
        int totalPoint = 0;

        for(Transaction transaction: transactions) {
            int amount = (int) Math.floor(transaction.getAmount());
            totalPoint += calculatePoint(amount);
        }

        return totalPoint;
    }

    private int calculatePoint(int amount) {
        int point;

        if(amount > 100) {
            point = 50 + (2 * (amount - 100));
        } else if(amount > 50) {
            point = amount - 50;
        } else {
            point = 0;
        }

        return point;
    }

    @Override
    public void delete(long transactionId) {
        transactionRepository.deleteByTransactionId(transactionId);
    }
}
