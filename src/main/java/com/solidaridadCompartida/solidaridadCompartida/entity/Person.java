

package com.solidaridadCompartida.solidaridadCompartida.entity;

import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



@Entity
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
