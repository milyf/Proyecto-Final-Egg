
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 public String formDonor(@RequestParam(required=false) String password,@RequestParam(required=false) String password2, @RequestParam(required=false) String email,
         @RequestParam(required=false) String name, @RequestParam(required=false) String donor_type, @RequestParam(required=false,defaultValue="0") Integer voluntary, ModelMap model){
 
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
    
@PreAuthorize("hasAnyRole('ROLE_DONOR')")
@GetMapping("/update")
public String updateDonor(ModelMap model){
return "edit_donor.html";

}


@PreAuthorize("hasAnyRole('ROLE_DONOR')")
@PostMapping("/update/form")
public String updateFormDonor(@RequestParam(required=false) String password, @RequestParam(required=false) String email,
         @RequestParam(required=false) String name, @RequestParam(required=false) String donor_type, @RequestParam(required=false,defaultValue="0") Integer voluntary, ModelMap model){
    
    try {
        donorservice.modifyDonor(email, password, name, voluntary);
     
        model.put("success", "Su usuario fue actualizado correctamente");
        
        
    } catch (MyException ex) {
        
        model.put("error", ex.getMessage());
        model.put("emai",email);
        model.put("password",password);
        model.put("name",name);
     
        return "/update/form?error=true"; 
    }

return "redirect:/indexD?success=true";

}  

}
