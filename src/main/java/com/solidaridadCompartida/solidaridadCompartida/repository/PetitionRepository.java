package com.solidaridadCompartida.solidaridadCompartida.repository;

import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.entity.Petition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, String> {

    @Query("SELECT p FROM Petition p WHERE p.id = :id")
    public Optional <Petition> findById(@Param("id") String id);

    @Query("SELECT p FROM Petition p WHERE p.beneficiary.name = :name")
    public List<Petition> searchByBeneficiary(@Param("name") String name);

    @Query("SELECT p FROM Petition p WHERE p.donor.name = :name")
    public List<Petition> searchByDonor(@Param("name") String name);

      
@Query("SELECT p FROM Petition p WHERE p.petitionType = :cosa")
public Optional<Petition> searchByToys(@Param("cosa") String cosa );    


     
}
