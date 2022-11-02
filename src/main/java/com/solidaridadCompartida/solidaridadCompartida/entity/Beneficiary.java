
package com.solidaridadCompartida.solidaridadCompartida.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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

      public List<String> requirementsBeneficiary() {

       

        List<String> results = new ArrayList<String>();

        if (this.getVoluntary() == 1) {

            results.add("Voluntarios");
        }

        if (this.getToys() == 1) {

            results.add("Juguetes");
        }

        if (this.getClothing() == 1) {

            results.add("Prendas de vestir");
        }

        if (this.getFood() == 1) {

            results.add("Alimentos");
        }

        if (this.getMonetary_aid() == 1) {

            results.add("Ayuda economica");
        }

        if (this.getSchool_supplies() == 1) {

            results.add("Útiles escolares");
        }

        if (this.getBooks() == 1) {

            results.add("Libros");
        }

        if (this.getMedical_supplies() == 1) {

            results.add("Medicamentos");
        }

        if (this.getFurnitures() == 1) {

            results.add("Muebles");
        }

        if (this.getLegacies() == 1) {

            results.add("Legado");
        }

        return results;

    }
    

    
    
}
