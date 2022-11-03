package com.solidaridadCompartida.solidaridadCompartida.entity;

import java.util.Base64;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
public class Post {
    
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private String title;
   private String body;
   
   @OneToOne
   private Image image;
   
   private Boolean alta;
   
   @ManyToOne()
   @JoinColumn(name="beneficiary_id", nullable=false)
   private Beneficiary beneficiary;
   
   public String getImgData() {
       Image image = this.getImage();
        return Base64.getMimeEncoder().encodeToString(image.getContent());
    } 

   
   //imagen, cuerpo, titulo, fecha de creacion.
       
}
