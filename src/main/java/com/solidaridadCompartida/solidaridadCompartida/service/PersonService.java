
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Service
public class PersonService  implements UserDetailsService   {
 
 @Autowired
 private PersonRepository personRepository;
 
 @Transactional
 public void createPerson(String password,String password2, String email,Rol rol) throws MyException {
     
 validate(password,email);
 checkPassword(password,password2);

 Person person = new Person();
 
 person.setPassword(password);
 person.setEmail(email);
 person.setRol(rol);
 person.setAlta(Boolean.TRUE);
 
 personRepository.save(person);
 
 }  

 public List<Person> ListPerson(){
 
 List<Person> people = new ArrayList();
 
 people = personRepository.findAll();
 
 return people;
 
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

 private void validate(String password, String email) throws MyException{

 
 
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
 
 throw new MyException("El nombre de usuario no puede ser nulo o estar vacío");
 } 
 
 if(password.isEmpty() || password==null){
 
 throw new MyException("La contraseña no puede ser nula o estar vacía");
 }
 

 if(personRepository.searchByEmail(email)==null){
 
 throw new MyException("El nombre de usuario no está registrado");
 
 }else{  
 Optional<Person> response = personRepository.searchByEmail(email);
 

    Person person = response.get();
    person.setEmail(email);
    person.setPassword(password);

         Boolean x=person.getPassword().equals(password);
         if(x==false){
         
         throw new MyException("La contraseña ingresada no es correcta");
          }
 }
 
 }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
               Optional<Person> response = personRepository.searchByEmail(email);
   
if(response.isPresent()){
Person person = response.get();

List<GrantedAuthority> permissions = new ArrayList();

GrantedAuthority p= new SimpleGrantedAuthority("ROLE_" + person.getRol().toString());
        
 permissions.add(p);
 
 ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
 
 HttpSession session=attr.getRequest().getSession(true);
 session.setAttribute("personsession", person);
        
return new User(person.getEmail(),person.getPassword(),permissions);
   
}else{

throw new UsernameNotFoundException("No se pudo cargar el usuario");

}
    }
 
 

 
}   
   
    
    

