
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import com.solidaridadCompartida.solidaridadCompartida.repository.DonorRepository;
import com.solidaridadCompartida.solidaridadCompartida.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

 @Autowired
 private BeneficiaryRepository beneficiaryRepository;
    
  @Autowired
 private DonorRepository donorRepository;   
    
  @Autowired
 private PersonRepository personRepository;   
    
 public void HardDeleteUserByEmail(String email)throws UsernameNotFoundException{
 
           Optional<Person> response = personRepository.searchByEmail(email);
   
if(response.isPresent()){
Person person = response.get();
 
personRepository.delete(person);
 
 } else {

throw new UsernameNotFoundException("El usuario no existe");
}  
    
 }  
    
}
