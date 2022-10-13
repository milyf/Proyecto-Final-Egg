
package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Data
public class Donor extends Person {
    
  
   private String name;
   private String donor_type;
   private Integer voluntary;
   

  
 
    
    
}
