
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.entity.Petition;
import com.solidaridadCompartida.solidaridadCompartida.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private PetitionService petitionServ;
    
    @GetMapping("/petition/{id}")
    public ResponseEntity<byte[]> image(@PathVariable String id){
        Petition petition= petitionServ.getOne(id);
        //AREGLO PARA TRAER EL CONTENIDO PARA QUE SE PROYECTE EN LA WEB
       byte[] image= petition.getImage().getContenido();
       
       //esto va a decir que tipo de contenido es, en este caso es una imagen
       HttpHeaders headers =new HttpHeaders();
       headers.setContentType(MediaType.IMAGE_JPEG);
       
       //estado en que termina el proceso... estadohttp--> estado 200 ok!
       
       
       return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
