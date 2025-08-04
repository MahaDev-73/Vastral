package com.sunbeam.services;

import java.util.List;

import com.sunbeam.entities.Order;
import com.sunbeam.entities.Seller;
import com.sunbeam.entities.Transaction;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}
