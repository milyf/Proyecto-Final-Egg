

package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



@Getter @Setter 
@Entity
public class Person {
    
    
       @Id
    private String username;
    
    private String password;
    
    private String email;
    
    private String user_type; 

    public Person() {
    }

    public Person(String username, String password, String email, String user_type) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_type = user_type;
    }

  
     
    
}
