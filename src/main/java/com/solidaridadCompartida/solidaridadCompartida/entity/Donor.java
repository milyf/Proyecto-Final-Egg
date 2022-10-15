
package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Entity;

import lombok.Data;



@Entity
@Data
public class Donor extends Person {
    
  
   private String name;
   private String donor_type;
   private Integer voluntary;
   

  
 
    
    
}
