
package com.solidaridadCompartida.solidaridadCompartida.entity;

import java.lang.reflect.Array;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Getter @Setter
@Entity
public class Beneficiary {
    
    @Id
    private String username;
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

    public Beneficiary() {
    }

    public Beneficiary(String username, String name, String institution_type, Integer voluntary, Integer toys, Integer clothing, Integer food, Integer monetary_aid, Integer school_supplies, Integer books, Integer medical_supplies, Integer furnitures, Integer legacies) {
        this.username = username;
        this.name  = name;
        this.institution_type = institution_type;
        this.voluntary = voluntary;
        this.toys = toys;
        this.clothing = clothing;
        this.food = food;
        this.monetary_aid = monetary_aid;
        this.school_supplies = school_supplies;
        this.books = books;
        this.medical_supplies = medical_supplies;
        this.furnitures = furnitures;
        this.legacies = legacies;
    }

    

    
    
}
