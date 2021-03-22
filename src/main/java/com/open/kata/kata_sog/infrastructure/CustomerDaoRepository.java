/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.infrastructure;

import com.open.kata.kata_sog.domaine.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Propriétaire
 */
public interface CustomerDaoRepository extends JpaRepository<Customer, Long>{
    
}
