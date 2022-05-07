package com.example.hkassessment;

import com.example.hkassessment.entity.Customer;
import com.example.hkassessment.entity.Transaction;
import com.example.hkassessment.service.TransactionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HkAssessmentApplicationTests {
    @Autowired
    private TransactionService transactionService;

    @Test
    void contextLoads() {
    }

    @Test
    void pointsPerMonthTest() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(120.00, new Customer("John Doe"), 1));
        transactions.add(new Transaction(75.88, new Customer("John Doe"), 1));
        transactions.add(new Transaction(125.50, new Customer("John Doe"), 2));
        transactions.add(new Transaction(57.00, new Customer("John Doe"), 3));
        transactions.add(new Transaction(68.00, new Customer("John Doe"), 3));
        Map<Integer, Integer> map = transactionService.calculatePointsPerMonth(transactions);

        Assertions.assertThat(map.get(1)).isEqualTo(115);
        Assertions.assertThat(map.get(2)).isEqualTo(100);
        Assertions.assertThat(map.get(3)).isEqualTo(25);
    }

    @Test
    void totalPointsTest() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(120.00, new Customer("John Doe"), 1));
        transactions.add(new Transaction(75.88, new Customer("John Doe"), 1));
        transactions.add(new Transaction(125.50, new Customer("John Doe"), 2));
        transactions.add(new Transaction(57.00, new Customer("John Doe"), 3));
        transactions.add(new Transaction(68.00, new Customer("John Doe"), 3));

        int totalPoint = transactionService.calculateTotalPoints(transactions);

        Assertions.assertThat(totalPoint).isEqualTo(240);
    }
}
