package com.sunbeam.services;




import java.util.List;

import org.hibernate.query.Order;

import com.sunbeam.entities.Orders;
import com.sunbeam.entities.Seller;
import com.sunbeam.entities.Transaction;

public interface TransactionService {

    //Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
    Transaction createTransaction(Order order);
	Transaction createTransaction(Orders order);
}
