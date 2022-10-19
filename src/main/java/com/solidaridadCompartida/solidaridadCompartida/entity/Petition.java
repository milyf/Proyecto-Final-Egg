package com.solidaridadCompartida.solidaridadCompartida.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Petition {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private Beneficiary beneficiary;

    @OneToOne
    private Donor donor;

    @OneToOne
    private Image image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    private Boolean aceptation;
    private Boolean alta;

    private Boolean voluntary;
    private String voluntaryType;

    private String petitionType;
    
    //selectFRONT: 
    /*private Integer toys;
    private Integer clothing;
    private Integer food;
    private Integer monetary_aid;
    private Integer school_supplies;
    private Integer books;
    private Integer medical_supplies;
    private Integer furnitures;
    private Integer legacies;
*/
}
