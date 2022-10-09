
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.DonorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {
    
@Autowired
 private DonorRepository donorRepository;
 
 @Transactional
 public void createDonor(String username, String name, String donor_type, Integer voluntary) throws MyException {
 
 validate(username,name);    
     
Donor donor = new Donor();

donor.setUsername(username);
donor.setName(name);
donor.setDonor_type(donor_type);
donor.setVoluntary(voluntary);
 
 donorRepository.save(donor);
 
 } 

 public List<Donor> ListDonor(){

List<Donor> donors = new ArrayList();
 
 donors = donorRepository.findAll();
 
 return donors;


} 
 
 public void modifyDonor(String username, String name, Integer voluntary) throws MyException{
 
 validate(username,name);   
 
 Optional<Donor> response = donorRepository.findById(username);
   
if(response.isPresent()){

    Donor donor = response.get();
    donor.setName(name);
    donor.setVoluntary(voluntary);
    donorRepository.save(donor);

}





}
 
 private void validate(String username, String name) throws MyException{

 if( username.isEmpty() || username==null){
 
 throw new MyException("El nombre de usuario no puede ser nulo o estar vacío");
 } 
 
 if(name.isEmpty() || name==null){
 
 throw new MyException("El nombre no puede ser nulo o estar vacía");
 }
 
 }
 
    
}
