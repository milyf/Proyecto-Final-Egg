
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService implements UserDetailsService {
    
 @Autowired
 private BeneficiaryRepository beneficiaryRepository;
 
 @Transactional
 public void createBeneficiary(String id,String name,String email, String institution_type, Integer voluntary,Integer toys, Integer clothing, Integer food, Integer monetary_aid, Integer school_supplies, Integer books, Integer medical_supplies, Integer furnitures, Integer legacies)throws MyException {
 
 validate(id,name,email);  
 
 Beneficiary beneficiary = new Beneficiary();
 
 
 beneficiary.setName(name);
 beneficiary.setInstitution_type(institution_type);
 beneficiary.setVoluntary(voluntary);
 beneficiary.setToys(toys);
 beneficiary.setClothing(clothing);
 beneficiary.setFood(food);
 beneficiary.setMonetary_aid(monetary_aid);
 beneficiary.setSchool_supplies(school_supplies);
 beneficiary.setBooks(books);
 beneficiary.setMedical_supplies(medical_supplies);
 beneficiary.setFurnitures(furnitures);
 beneficiary.setLegacies(legacies);
 beneficiary.setRol(Rol.BENEFICIARY);
 beneficiary.setAlta(Boolean.TRUE);
 beneficiaryRepository.save(beneficiary);
 
 
 } 
  
 public List<Beneficiary> ListBeneficiary(){
 
 List<Beneficiary> beneficiaries = new ArrayList();
 
 beneficiaries = beneficiaryRepository.findAll();
 
 return beneficiaries;
 
 
 
 
 }
 
 public void modifyDonor(String username, String name, Integer voluntary, Integer toys, Integer clothing, Integer food, Integer monetary_aid, Integer school_supplies, Integer books, Integer medical_supplies, Integer furnitures, Integer legacies) throws MyException{
 
 validate(username,name);    
 Optional<Beneficiary> response = beneficiaryRepository.findById(username);
   
if(response.isPresent()){

    Beneficiary beneficiary = response.get();
    beneficiary.setName(name);
    beneficiary.setVoluntary(voluntary);
    beneficiary.setToys(toys);
    beneficiary.setClothing(clothing);
    beneficiary.setFood(food);
    beneficiary.setMonetary_aid(monetary_aid);
    beneficiary.setSchool_supplies(school_supplies);
    beneficiary.setBooks(books);
    beneficiary.setMedical_supplies(medical_supplies);
    beneficiary.setFurnitures(furnitures);
    beneficiary.setLegacies(legacies);
    beneficiaryRepository.save(beneficiary);

}

  
 } 
 
 private void validate(String name, String password, String email) throws MyException{

if(name.isEmpty() || name==null){
 
 throw new MyException("El nombre no puede ser nulo o estar vacío");
 }
 
 if(password.isEmpty() || password==null){
 
 throw new MyException("La contraseña no puede ser nula o estar vacía");
 }
 
 if(email.isEmpty() || email==null){
 
 throw new MyException("El email no puede ser nulo o estar vacío");
 }
 
 Boolean correctPassword;
 correctPassword=this.validatePassword(password);
 if(correctPassword==false){
 
 throw new MyException("La contraseña debe tener al menos un número, un caracter en minúscula y uno en mayúsculas");
 
 }
 
 }
 
 public void checkPassword(String password, String password2) throws MyException{
 
 Boolean check= password.equals(password2);
 if(check==false){
 
 throw new MyException("No coincide la contraseña");
 
 }
 
 }
 
 public List<String> ListInstitutionsTypes(){

List<String> institutions_types= new ArrayList();

institutions_types.add("Iglesias");
institutions_types.add("Escuelas");
institutions_types.add("Comedores");
institutions_types.add("Merenderos");

return institutions_types;

} 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
 
 
 
}
