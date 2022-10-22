
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
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
  
  private void validateDonor(String name, String donor_type) throws MyException{

 
 if(name.isEmpty() || name==null){
 
 throw new MyException("El nombre no puede ser nulo o estar vacío");
 }
 
  
 if(donor_type.isEmpty() || donor_type==null){
 
 throw new MyException("El tipo de donador no puede ser nulo o estar vacío");
 }
 
 }
  
 public Person GetPerson(String email) throws UsernameNotFoundException{
     Optional<Person> response = personRepository.searchByEmail(email);
     if (response.isPresent()){
     Person person = response.get();
      return person;}
     else {
     throw new UsernameNotFoundException("El usuario no existe");
     }
 
 }
 
 public Donor GetDonor(String email) throws UsernameNotFoundException{
     Optional<Donor> response = donorRepository.searchByEmail(email);
     if (response.isPresent()){
     Donor donor = response.get();
      return donor;}
     else {
     throw new UsernameNotFoundException("El usuario no existe");
     }
 
 }
 
  public void modifyDonor(String email,String name,String donor_type,Integer voluntary) throws MyException{
 validateDonor(name,donor_type);
 Optional<Donor> response = donorRepository.searchByEmail(email);
   
if(response.isPresent()){

    Donor donor = response.get();
    donor.setName(name);
    donor.setDonor_type(donor_type);
    donor.setVoluntary(voluntary);
    donorRepository.save(donor);

}else {throw new MyException("");}





}

    
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
