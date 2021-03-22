/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.controller;

import com.open.kata.kata_sog.domaine.entities.Operation;
import com.open.kata.kata_sog.domaine.services.IBankService;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FHE
 */
@RestController
public class ControllerAccount {

    @Autowired
    IBankService bankService;
    Operation op;
    static Logger logger = Logger.getLogger(ControllerAccount.class.getName());

    @GetMapping("/operationHistory")
    public List<Operation> getListOperation() {
        List<Operation> listOperation = bankService.getOperationHistory();
        return listOperation;

    }

    @PostMapping("/depositOperation")
    public Operation depositOperation(@Valid @RequestBody Operation operation) {
        return bankService.depositOperation(operation);
    }

    @PostMapping("/withdrawalOperation")
    public Operation withdrawalOperation(@Valid @RequestBody Operation operation) {
        return bankService.withdrawalOperation(operation);
    }

    @GetMapping("/operationHistoryByAccount")
    public Page<Operation> getListOperationByAccount(@RequestParam Long code ,  @RequestParam(name="page",defaultValue="0")int page, @RequestParam(name="size",defaultValue="5")int size) {
        Page<Operation> listOperation = bankService.getOperationHistoryByAccount(code, page, size);
        return listOperation;
    }

    @GetMapping("/lastOperation")
    public String getLastOperation() {
        String operationType = bankService.getLastOperation();
        return operationType;

    }
}
