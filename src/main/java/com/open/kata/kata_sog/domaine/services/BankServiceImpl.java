/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.domaine.services;

import com.open.kata.kata_sog.domaine.entities.Account;
import com.open.kata.kata_sog.domaine.entities.Customer;
import com.open.kata.kata_sog.domaine.entities.Operation;
import com.open.kata.kata_sog.infrastructure.AccountDaoRepository;
import com.open.kata.kata_sog.infrastructure.CustomerDaoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.open.kata.kata_sog.infrastructure.OperationDaoRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author FHE
 */
@Data
@Service
public class BankServiceImpl implements IBankService {

    @Autowired
    private OperationDaoRepository operationDaoRepository;
    @Autowired
    private AccountDaoRepository accountDaoRepository;
    @Autowired
    private CustomerDaoRepository customerDaoRepository;
    List<Operation> listOperation = new ArrayList();

    @Override
    public Operation saveOperation(Operation o) {
        return operationDaoRepository.save(o);
    }

    @Override
    public Customer saveCustomer(Customer c) {
        return customerDaoRepository.save(c);
    }

    @Override
    public Account saveAccount(Account a) {
        return accountDaoRepository.save(a);
    }

    @Override
    public List<Operation> getOperationHistory() {
        return operationDaoRepository.findAll();
    }

    @Override
    public Page<Operation> getOperationHistoryByAccount(Long code, int page, int size) {
        return operationDaoRepository.getOperationHistoryByAccount(code, PageRequest.of(page, size));
    }

    @Override
    public Operation depositOperation(Operation operation) {
        if (operationDaoRepository != null) {
            if (operation.getAmount() > 0) {
                Account acc = accountDaoRepository.getOne(operation.getAccount().getNumAccount());
                acc.setBalance(acc.getBalance() + operation.getAmount());
                operation.setNewBbalance(acc.getBalance());
                accountDaoRepository.save(acc);
                return operationDaoRepository.save(operation);

            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Operation withdrawalOperation(Operation operation) {
        if (operationDaoRepository != null) {
             Account acc = accountDaoRepository.getOne(operation.getAccount().getNumAccount());
            if (operation.getAmount() > 0 && operation.getAmount() <= acc.getDebitMax()) {
                acc.setBalance(acc.getBalance() - operation.getAmount());
                operation.setNewBbalance(acc.getBalance());
                accountDaoRepository.save(acc);
                return operationDaoRepository.save(operation);
            } else {
                System.out.println("Operation non autorisé : Solde insuffisante " + operation.getAccount().getBalance());
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String getLastOperation() {
        Optional<Operation> o = operationDaoRepository.findFirstByOrderByCodeOperationAsc();
        return o.map(Operation::getOperation).orElse("not found");
    }

}
