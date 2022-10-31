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


@Query("SELECT b FROM Beneficiary b WHERE b.toys = :toys ")
public List<Beneficiary> searchByToys(@Param("toys") Integer toys); 


@Query("SELECT b FROM Beneficiary b WHERE b.clothing = :clothing ")
public List<Beneficiary> searchByClothing(@Param("clothing") Integer clothing); 

@Query("SELECT b FROM Beneficiary b WHERE b.food = :food ")
public List<Beneficiary> searchByFood(@Param("food") Integer food); 

@Query("SELECT b FROM Beneficiary b WHERE b.monetary_aid = :monetary_aid ")
public List<Beneficiary> searchByMonetaryAid(@Param("monetary_aid") Integer monetary_aid); 

@Query("SELECT b FROM Beneficiary b WHERE b.school_supplies = :school_supplies ")
public List<Beneficiary> searchBySchoolSupplies(@Param("school_supplies") Integer school_supplies); 

@Query("SELECT b FROM Beneficiary b WHERE b.books = :books ")
public List<Beneficiary> searchByBooks(@Param("books") Integer books);

@Query("SELECT b FROM Beneficiary b WHERE b.medical_supplies = :medical_supplies ")
public List<Beneficiary> searchByMedicalSupplies(@Param("medical_supplies") Integer medical_supplies);

@Query("SELECT b FROM Beneficiary b WHERE b.furnitures = :furnitures ")
public List<Beneficiary> searchByFurnitures(@Param("furnitures") Integer furnitures);

@Query("SELECT b FROM Beneficiary b WHERE b.legacies = :legacies ")
public List<Beneficiary> searchByLegacies(@Param("legacies") Integer legacies);



    
}
