/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidaridadCompartida.solidaridadCompartida.repository;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, String> {
    
@Query("SELECT b FROM Beneficiary b WHERE b.email = :email")

public Optional<Beneficiary> searchByEmail(@Param("email") String email);    
    
@Query("SELECT b FROM Beneficiary b WHERE b.name = :name")

public Beneficiary searchByname(@Param("name") String name);

@Query("SELECT b FROM Beneficiary b WHERE b.institution_type = :institution_type ")

public List<Beneficiary> searchByInstitutionType(@Param("institution_type") String institution_type );     
    
@Query("SELECT b FROM Beneficiary b WHERE b.voluntary = :voluntary ")

public List<Beneficiary> searchByVoluntary(@Param("voluntary") Integer voluntary);     
    
    
}
