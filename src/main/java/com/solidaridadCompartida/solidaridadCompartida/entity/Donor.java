
package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

@Entity
public class Donor {
    
   @Id
   private String username; 
   private String name;
   private String donor_type;
   private Integer voluntary;

    public Donor() {
    }

    public Donor(String username, String name, String donor_type, Integer voluntary) {
        this.username = username;
        this.name = name;
        this.donor_type = donor_type;
        this.voluntary = voluntary;
    }



 
  

   
  

  
 
    
    
}
