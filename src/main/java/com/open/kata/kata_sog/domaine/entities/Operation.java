/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.domaine.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author FHE
 */
@Component
@Entity
@Table(name="operation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements Serializable {

    @Id
    @GeneratedValue
    private Long codeOperation;
    private String operation;
    private LocalDate date;
    private double amount; 
    private double newBbalance;
    @ManyToOne
    @JoinColumn(name="numAccount")
    private Account account;
}
