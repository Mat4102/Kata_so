package com.open.kata.kata_sog;

//import com.open.kata.kata_sog.domaine.entities.Operation;
import com.open.kata.kata_sog.domaine.entities.Account;
import com.open.kata.kata_sog.domaine.entities.Customer;
import com.open.kata.kata_sog.domaine.entities.Operation;
import com.open.kata.kata_sog.domaine.services.BankServiceImpl;
import java.time.LocalDate;
//import com.open.kata.kata_sog.infrastructure.AccoutDaoRepository;
//import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KataSogApplication implements CommandLineRunner {

    @Autowired
    private BankServiceImpl bankServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(KataSogApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Customer customer = new Customer(null, "Sofia", "Marianne", LocalDate.now(), null);
        Account account = new Account(null, 300.0, LocalDate.now(), 0.0, null, customer);
        bankServiceImpl.saveCustomer(customer);
        bankServiceImpl.saveAccount(account);
   
    }
}
