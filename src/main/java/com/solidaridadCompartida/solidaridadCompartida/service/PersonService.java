
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PersonService   {
 
 @Autowired
 private PersonRepository personRepository;
 
 @Transactional
 public void createPerson(String password, String email, String user_type) throws MyException {
     
 validate(password,email);


 Person person = new Person();
 
 person.setPassword(password);
 person.setEmail(email);

 
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

 private void validate(String username, String password, String email) throws MyException{

 if( username.isEmpty() || username==null){
 
 throw new MyException("El nombre de usuario no puede ser nulo o estar vacío");
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
 
 public void loginPerson(String username, String password) throws MyException{
 
      if( username.isEmpty() || username==null){
 
 throw new MyException("El nombre de usuario no puede ser nulo o estar vacío");
 } 
 
 if(password.isEmpty() || password==null){
 
 throw new MyException("La contraseña no puede ser nula o estar vacía");
 }
 

 if(personRepository.searchByUsername(username)==null){
 
 throw new MyException("El nombre de usuario no está registrado");
 
 }else{  Person person = new Person();
 
         person=personRepository.searchByUsername(username);
         Boolean x=person.getPassword().equals(password);
         if(x==false){
         
         throw new MyException("La contraseña ingresada no es correcta");
          }
 }
 
 }
 
 

 
}   
    
    
    

