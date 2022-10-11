
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;
import com.solidaridadCompartida.solidaridadCompartida.service.PersonService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/donor")
public class DonorController {
    
 @Autowired DonorService donorservice;
 
 @Autowired PersonService personservice;
 
 @GetMapping("/register")
 public String registerDonor(){
 
 return "donor_form.html";
 
 }   
    
 @PostMapping("/form") 
 public String formDonor(@RequestParam String username,@RequestParam String password, @RequestParam String email,
         @RequestParam String name, @RequestParam String donor_type, @RequestParam Integer voluntary, ModelMap model){
 
     try {String user_type="d";
         personservice.createPerson(username, password, email, user_type);
         donorservice.createDonor(username, name, donor_type, voluntary);
         model.put("success", "Su usuario fue registrado correctamente");
     } catch (MyException ex) {
         model.put("error", ex.getMessage());
         return "donor_form.html";
     }
 
 return "index.html";
 
 
 }
    
    
}
