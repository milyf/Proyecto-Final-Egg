
package com.solidaridadCompartida.solidaridadCompartida.entity;

import java.lang.reflect.Array;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Data
public class Beneficiary extends Person {
    
   
    private String name;
    private String institution_type;
    private Integer voluntary;
    private Integer toys;
    private Integer clothing;
    private Integer food;
    private Integer monetary_aid;
    private Integer school_supplies;
    private Integer books;
    private Integer medical_supplies;
    private Integer furnitures;
    private Integer legacies;

    
    

    
    
}
