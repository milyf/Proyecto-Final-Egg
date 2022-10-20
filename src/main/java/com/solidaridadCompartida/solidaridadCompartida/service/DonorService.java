
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.DonorRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DonorService implements UserDetailsService {
    
@Autowired
 private DonorRepository donorRepository;
 
 @Transactional
 public void createDonor(String email, String password, String password2, String name, String donor_type, Integer voluntary) throws MyException {
 
 validate(email,password,name); 
 checkPassword(password,password2);
     
Donor donor = new Donor();

donor.setEmail(email);
donor.setPassword(new BCryptPasswordEncoder().encode(password));
donor.setName(name);
donor.setDonor_type(donor_type);
donor.setVoluntary(voluntary);
donor.setRol(Rol.DONOR);
 donor.setAlta(Boolean.TRUE);
 
 donorRepository.save(donor);
 
 } 

 public List<Donor> ListDonor(){

List<Donor> donors = new ArrayList();
 
 donors = donorRepository.findAll();
 
 return donors;


} 
 
 public void modifyDonor(String email,String password,String name,Integer voluntary) throws MyException{
 
 validate(email,password,name);   
 
 Optional<Donor> response = donorRepository.searchByEmail(email);
   
if(response.isPresent()){

    Donor donor = response.get();
    donor.setName(name);
    donor.setVoluntary(voluntary);
    donorRepository.save(donor);

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
 
 private void validate(String email, String password,String name) throws MyException{

 
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
 

 if(donorRepository.searchByEmail(email)==null){
 
 throw new MyException("El email no está registrado");
 
 }else{  
 
       
 Optional<Donor> response = donorRepository.searchByEmail(email);
 

    Donor donor = response.get();
    donor.setEmail(email);
    donor.setPassword(password);


 Boolean x=donor.getPassword().equals(password);
      
         if(x==false){
         
         throw new MyException("La contraseña ingresada no es correcta");
          }
 }
 
 }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
        Optional<Donor> response = donorRepository.searchByEmail(email);
   
if(response.isPresent()){
Donor donor = response.get();

List<GrantedAuthority> permissions = new ArrayList();

GrantedAuthority p= new SimpleGrantedAuthority("ROLE_" + donor.getRol().toString());
        
 permissions.add(p);
        
return new User(donor.getEmail(),donor.getPassword(),permissions);
   
}else{


return null;

}
        
    }

    
}
