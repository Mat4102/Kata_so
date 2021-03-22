/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.open.kata.kata_sog.infrastructure;

import com.open.kata.kata_sog.domaine.entities.Operation;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FHE
 */
@Repository
public interface OperationDaoRepository extends JpaRepository<Operation, Long> {

    public Optional<Operation> findFirstByOrderByCodeOperationAsc();

    @Query("select o from Operation o where o.account.numAccount = :code")
    public Page<Operation> getOperationHistoryByAccount(@Param("code") Long code, Pageable p);

}
