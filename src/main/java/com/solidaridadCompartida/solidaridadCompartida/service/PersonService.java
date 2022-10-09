
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
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
 public void createPerson(String username, String password, String email, String user_type) throws MyException {
     
 validate(username,password,email);


 Person person = new Person();
 
 person.setUsername(username);
 person.setPassword(password);
 person.setEmail(email);
 person.setUser_type(user_type);
 
 personRepository.save(person);
 
 }  

 public List<Person> ListPerson(){
 
 List<Person> people = new ArrayList();
 
 people = personRepository.findAll();
 
 return people;
 
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


}

}   
    
    
    

