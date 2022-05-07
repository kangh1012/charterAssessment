package com.example.hkassessment.repository;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomer(Customer customer);

    void deleteByTransactionId(long transactionId);
}
