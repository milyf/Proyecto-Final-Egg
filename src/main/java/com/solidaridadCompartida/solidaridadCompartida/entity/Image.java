 
package com.solidaridadCompartida.solidaridadCompartida.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Image {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idImage;
    private String mime; //asigna formato de archivo de imagen
    private String name;
    
    @Lob @Basic(fetch = FetchType.LAZY)
     private byte[] contenido;
    
 //lob es que tal vez sea grande, basic es como va a ser el tipo de carga: lazy se carga solo cuando se hace un get sobre el, el resto es eager por defecto
    
    
    
    
    
    
    
    
    
}
