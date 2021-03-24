/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.controllerUnitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.open.kata.kata_sog.controller.ControllerAccount;
import com.open.kata.kata_sog.domaine.entities.Account;
import com.open.kata.kata_sog.domaine.entities.Operation;
import com.open.kata.kata_sog.domaine.services.IBankService;

import java.time.LocalDate;

import org.junit.After;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author Propriétaire
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerAccountUnitTest {

    @Mock
    IBankService iBanqueServiceMock;

    @InjectMocks
    ControllerAccount controllerAccount;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerAccount).build();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOperationHistory method, of class ControllerAccount.
     */
    @Test
    public void testGetListOperation() throws Exception {

        // given
        Operation operation = new Operation();
        Account account = new Account();
        operation.setAccount(account);
        operation.setAmount(100.0);
        operation.setOperation("deposit");
        operation.getAccount().setNumAccount(1L);
        operation.setCodeOperation(1L);
        operation.setDate(LocalDate.now());
        List<Operation> listOperation = new ArrayList<>();
        listOperation.add(operation);

        // when
        when(iBanqueServiceMock.getOperationHistory())
                .thenReturn(listOperation);

        // then
        mockMvc.perform(get("/operationHistory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].operation", is("deposit")))
                .andExpect(jsonPath("$.[0].amount", is(100.0)))
                .andExpect(jsonPath("$.[0].account.numAccount", is(1)));

        verify(iBanqueServiceMock, times(1)).getOperationHistory();
        verifyNoMoreInteractions(iBanqueServiceMock);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test of depositOperation method, of class ControllerAccount.
     */
    @Test
    public void testDepositOperation() throws Exception {

        // given 
        Account account = new Account();
        Operation operation = new Operation();

        operation.setAccount(account);
        operation.setAmount(100.0);
        operation.setOperation("deposit");
        operation.getAccount().setNumAccount(1L);
        operation.setCodeOperation(1L);

        // when
        when(iBanqueServiceMock.depositOperation(operation))
                .thenReturn(operation);

        // then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/depositOperation")
                .content(asJsonString(operation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codeOperation").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").isNumber());

        verify(iBanqueServiceMock, times(1)).depositOperation(operation);
        verifyNoMoreInteractions(iBanqueServiceMock);

    }

    /**
     * Test of withdrawalOperation method, of class ControllerAccount.
     */
    @Test
    public void testwithdrawalOperation() throws Exception {

        // given 
        Account account = new Account();
        Operation operation = new Operation();

        operation.setAccount(account);
        operation.setAmount(200.0);
        operation.setOperation("withdrawal");
        operation.getAccount().setNumAccount(1L);
        operation.setCodeOperation(1L);

        // when
        when(iBanqueServiceMock.depositOperation(operation))
                .thenReturn(operation);

        // then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/depositOperation")
                .content(asJsonString(operation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codeOperation").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operation").value("withdrawal"));
        

        verify(iBanqueServiceMock, times(1)).depositOperation(operation);
        verifyNoMoreInteractions(iBanqueServiceMock);

    }
}
