
package com.solidaridadCompartida.solidaridadCompartida.controllers;


import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.ImageService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
BeneficiaryService beneficiaryservice;

@Autowired
ImageService imageservice;

@GetMapping(value="/beneficiary/profile/{id}", produces = MediaType.IMAGE_PNG_VALUE)
public ResponseEntity<byte[]> imageBeneficiary (@PathVariable String id){

Beneficiary beneficiary = beneficiaryservice.getOne(id);
    Image img = new Image();
    try {
      img = imageservice.loadFromBeneficiary(beneficiary);
    } catch (MyException ex) {
        System.out.println(ex.getMessage()); 
    }
     byte[] image= img.getContent();

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.IMAGE_PNG); //parseMediaType(img.getMime())
    ResponseEntity<byte[]> resp = new ResponseEntity<>(image,headers,HttpStatus.OK); 
    return resp;
}
    
}
