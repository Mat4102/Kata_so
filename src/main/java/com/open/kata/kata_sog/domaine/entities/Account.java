/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.domaine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Propriétaire
 */
@Component
@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "numAccount")
    private Long numAccount;
    private double debitMax;
    private LocalDate creatDate;
    private double balance; 
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Operation> operationList;
    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;
}
