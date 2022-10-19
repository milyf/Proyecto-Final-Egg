
package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Data
public class Donor extends Person {
    
        
   private String name;
   private String donor_type;// SelectFRONT:Particular, Empresa Privada, ONG
  
   
   private Boolean voluntary;
    private String voluntaryType;//SelectFRONT:Clases Particulares, Medicina, Ingenieria, Jardineria
    

  
 
    
    
}
