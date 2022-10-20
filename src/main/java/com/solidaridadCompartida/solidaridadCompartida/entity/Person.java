

package com.solidaridadCompartida.solidaridadCompartida.entity;

import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
@Data
public class Person {
    
    
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String password;
    
    private String email;

   @Enumerated(EnumType.STRING)
    private Rol rol;
   
    private Boolean alta;  
    
}
