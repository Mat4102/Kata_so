/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.domaine.services;

import com.open.kata.kata_sog.domaine.entities.Account;
import com.open.kata.kata_sog.domaine.entities.Customer;
import com.open.kata.kata_sog.domaine.entities.Operation;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author FHE
 */
public interface IBankService {

    public List<Operation> getOperationHistory();
    
    public Page<Operation> getOperationHistoryByAccount(Long code, int page, int size);
    
    public Customer saveCustomer(Customer c);
    
    public Operation saveOperation(Operation o);
    
    public Account saveAccount(Account a);

    public Operation depositOperation(Operation o);

    public Operation withdrawalOperation(Operation o);

    public String getLastOperation();
}
