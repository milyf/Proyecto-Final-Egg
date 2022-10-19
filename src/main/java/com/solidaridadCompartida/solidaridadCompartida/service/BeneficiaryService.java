
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService implements UserDetailsService {
    
 @Autowired
 private BeneficiaryRepository beneficiaryRepository;
 
 @Transactional
 public void createBeneficiary(String email,String password,String password2,String name, String institution_type)throws MyException {
 
 validate(email,password,name);  
 checkPassword(password,password2);
 
 Beneficiary beneficiary = new Beneficiary();
 
 beneficiary.setEmail(email);
 beneficiary.setPassword(password);
 beneficiary.setName(name);
 beneficiary.setInstitution_type(institution_type);

 beneficiary.setRol(Rol.BENEFICIARY);
 beneficiary.setAlta(Boolean.TRUE);
 beneficiaryRepository.save(beneficiary);
 
 
 } 
  
 public List<Beneficiary> ListBeneficiary(){
 
 List<Beneficiary> beneficiaries = new ArrayList();
 
 beneficiaries = beneficiaryRepository.findAll();
 
 return beneficiaries;
 
 
 
 
 }
 
 public void modifyBeneficiary(String email,String password,String name) throws MyException{
 
 validate(email,password,name);    
 Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);
   
if(response.isPresent()){

    Beneficiary beneficiary = response.get();
    beneficiary.setName(name);
    
    beneficiaryRepository.save(beneficiary);
}

  
 } 
 
 public Boolean validatePassword(String str){
    char ch;
    boolean capitalFlag = false;
    boolean lowerCaseFlag = false;
    boolean numberFlag = false;
    
    for(int i=0;i < str.length();i++) {
        ch = str.charAt(i);
        if( Character.isDigit(ch)) {
            numberFlag = true;
        }
        else if (Character.isUpperCase(ch)) {
            capitalFlag = true;
        } else if (Character.isLowerCase(ch)) {
            lowerCaseFlag = true;
        }
        if(numberFlag && capitalFlag && lowerCaseFlag)
            return true;
    }
    return false;

}
 
 private void validate(String email,String password, String name) throws MyException{

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
 
 public void loginPerson(String email, String password) throws MyException{
 
  if( email.isEmpty() || email==null){
 
 throw new MyException("El email no puede ser nulo o estar vacío");
 } 
 
 if(password.isEmpty() || password==null){
 
 throw new MyException("La contraseña no puede ser nula o estar vacía");
 }
 

 if(beneficiaryRepository.searchByEmail(email)==null){
 
 throw new MyException("El email no está registrado");
 
 }else{  
 
       
 Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);
 

    Beneficiary beneficiary= response.get();
    beneficiary.setEmail(email);
    beneficiary.setPassword(password);


 Boolean x=beneficiary.getPassword().equals(password);
      
         if(x==false){
         
         throw new MyException("La contraseña ingresada no es correcta");
          }
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
                Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);
   
if(response.isPresent()){
Beneficiary beneficiary = response.get();

List<GrantedAuthority> permissions = new ArrayList();

GrantedAuthority p= new SimpleGrantedAuthority("ROLE_" + beneficiary.getRol().toString());
        
 permissions.add(p);
        
return new User(beneficiary.getEmail(),beneficiary.getPassword(),permissions);
   
}else{

return null;

}
    }

 
 
 
 
}
