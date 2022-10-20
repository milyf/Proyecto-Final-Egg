
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;

import java.util.List;
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
 

 
 @GetMapping("/register")
 public String registerDonor(){
 
 return "donor_form.html";
 
 }   
 
    
 @PostMapping("/form") 
 public String formDonor(@RequestParam String password,@RequestParam String password2, @RequestParam String email,
         @RequestParam String name, @RequestParam String donor_type, @RequestParam Integer voluntary, ModelMap model){
 
     try {
     donorservice.createDonor(email, password, password2, name, donor_type, voluntary);
         model.put("success", "Su usuario fue registrado correctamente");
     } catch (MyException ex) {
         model.put("error", ex.getMessage());
         model.put("emai",email);
        model.put("password",password);
        model.put("password2",password2);
        model.put("name",name);
       
         return "donor_form.html";
     }
 
 return "redirect:/?success=true";
 
 
 }
    
      @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Donor> donors = donorservice.ListDonor();
        
        modelo.put("donors",donors);
        return "list_donor.html";
    }
    
  
    
}
