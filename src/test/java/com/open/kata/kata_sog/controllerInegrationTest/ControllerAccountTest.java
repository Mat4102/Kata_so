/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.controllerInegrationTest;

import com.open.kata.kata_sog.domaine.entities.Account;
import com.open.kata.kata_sog.domaine.entities.Customer;
import com.open.kata.kata_sog.domaine.entities.Operation;
import java.lang.management.OperatingSystemMXBean;
import java.net.URI;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Propriétaire
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class ControllerAccountTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test of testDepositOperation method
     */
    @Test
    public void testDepositOperation() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI url = new URI("http://localhost:2021/depositOperation");

        Account account = new Account();
        Operation operation1 = new Operation();
        account.setNumAccount(2L);
        operation1.setOperation("deposit");
        operation1.setAmount(1500.0);
        operation1.setDate(LocalDate.now());
        operation1.setAccount(account);

        Operation operation2 = new Operation();
        account.setNumAccount(2L);
        operation2.setOperation("deposit");
        operation2.setAmount(200.50);
        operation2.setDate(LocalDate.now());
        operation2.setAccount(account);

        HttpEntity<Operation> requestEntity1 = new HttpEntity<>(operation1, headers);
        HttpEntity<Operation> requestEntity2 = new HttpEntity<>(operation2, headers);
        // when
        ResponseEntity<Operation> responseEntity1 = new RestTemplate().postForEntity(url, requestEntity1, Operation.class);
        ResponseEntity<Operation> responseEntity2 = new RestTemplate().postForEntity(url, requestEntity2, Operation.class);

        // then
        System.out.println("Status Code: " + responseEntity1.getStatusCode());
        System.out.println("Code Operation: " + responseEntity1.getBody().getCodeOperation());
        System.out.println("New Balance +: " + responseEntity1.getBody().getNewBbalance());
        System.out.println("Location: " + responseEntity1.getHeaders().getLocation());

        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity1.getBody().getNewBbalance()).isEqualTo(1500.0);

        System.out.println("New Balance: " + responseEntity1.getBody().getNewBbalance());
        assertThat(responseEntity2.getBody().getNewBbalance()).isEqualTo(1700.50);

    }

    /**
     * Test of WithdrawalOperation method
     */
    @Test
    public void testWithdrawalOperation() throws Exception {

        // given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI url = new URI("http://localhost:2021/withdrawalOperation");

        Account account = new Account();
        Operation operation1 = new Operation();
        account.setNumAccount(2L);
        operation1.setOperation("withdrawal");
        operation1.setAmount(300.0);
        operation1.setDate(LocalDate.now());
        operation1.setAccount(account);

        Operation operation2 = new Operation();
        account.setNumAccount(2L);
        operation2.setOperation("withdrawal");
        operation2.setAmount(200.0);
        operation2.setDate(LocalDate.now());
        operation2.setAccount(account);

        HttpEntity<Operation> requestEntity1 = new HttpEntity<>(operation1, headers);
        HttpEntity<Operation> requestEntity2 = new HttpEntity<>(operation2, headers);
        // when
        ResponseEntity<Operation> responseEntity1 = new RestTemplate().postForEntity(url, requestEntity1, Operation.class);
        ResponseEntity<Operation> responseEntity2 = new RestTemplate().postForEntity(url, requestEntity2, Operation.class);

        // then
        System.out.println("Status Code: " + responseEntity1.getStatusCode());
        System.out.println("Code Operation: " + responseEntity1.getBody().getCodeOperation());
        System.out.println("New Balance +: " + responseEntity1.getBody().getNewBbalance());
        System.out.println("Location: " + responseEntity1.getHeaders().getLocation());

        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity1.getBody().getNewBbalance()).isEqualTo(1400.50);

        System.out.println("New Balance: " + responseEntity1.getBody().getNewBbalance());

        assertThat(responseEntity2.getBody().getNewBbalance()).isEqualTo(1200.50);

    }
}
