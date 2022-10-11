
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {
    
 @Autowired
 private BeneficiaryRepository beneficiaryRepository;
 
 @Transactional
 public void createBeneficiary(String username, String name, String institution_type, Integer voluntary,Integer toys, Integer clothing, Integer food, Integer monetary_aid, Integer school_supplies, Integer books, Integer medical_supplies, Integer furnitures, Integer legacies)throws MyException {
 
 validate(username,name);  
 
 Beneficiary beneficiary = new Beneficiary();
 
 beneficiary.setUsername(username);
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
 
 private void validate(String username, String name) throws MyException{

 if( username.isEmpty() || username==null){
 
 throw new MyException("El nombre de usuario no puede ser nulo o estar vacío");
 } 
 
 if(name.isEmpty() || name==null){
 
 throw new MyException("El nombre no puede ser nulo o estar vacío");
 }
 
 
 }
 

 
 
 
 
}
