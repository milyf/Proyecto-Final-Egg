
package com.solidaridadCompartida.solidaridadCompartida.repository;


import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    
@Query("SELECT p FROM Post p WHERE p.beneficiary = :beneficiary ")
public List<Post> searchByBEneficiaryId(@Param("beneficiary") Beneficiary beneficiary); 
    
}
