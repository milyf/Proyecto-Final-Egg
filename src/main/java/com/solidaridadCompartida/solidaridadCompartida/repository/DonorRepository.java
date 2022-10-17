
package com.solidaridadCompartida.solidaridadCompartida.repository;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String> {
    
@Query("SELECT d FROM Donor d WHERE d.email = :email")

public Optional<Donor> searchByEmail(@Param("email") String email);    
    
@Query("SELECT d FROM Donor d WHERE d.name = :name")

public Donor searchByName(@Param("name") String name);
    
@Query("SELECT d FROM Donor d WHERE d.donor_type = :donor_type ")

public List<Donor> searchByType(@Param("donor_type") String donor_type);

@Query("SELECT d FROM Donor d WHERE d.voluntary = :voluntary ")

public List<Donor> searchByVoluntary(@Param("voluntary") Integer voluntary);

    
}
