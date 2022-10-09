
package com.solidaridadCompartida.solidaridadCompartida.repository;

import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    
@Query("SELECT p FROM Person p WHERE p.username = :username")

public Person searchByUsername(@Param("username") String username);    
    
@Query("SELECT p FROM Person p WHERE p.email = :email")

public Person searchByEmail(@Param("email") String email);
    
@Query("SELECT p FROM Person p WHERE p.user_type = :user_type ")

public List<Person> searchByType(@Param("user_type") String user_type);    
    
}
