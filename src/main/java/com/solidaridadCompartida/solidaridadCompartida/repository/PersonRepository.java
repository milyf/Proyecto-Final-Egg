
package com.solidaridadCompartida.solidaridadCompartida.repository;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    
@Query("SELECT p FROM Person p WHERE p.email = :email")

public Optional<Person> searchByEmail(@Param("email") String email);    
        
@Query("SELECT p FROM Person p WHERE p.rol = :rol ")

public Optional<Person> searchByType(@Param("rol") String rol);    
    
}
