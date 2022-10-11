
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
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
@RequestMapping("/beneficiary")
public class BeneficiaryController {

@Autowired
private BeneficiaryService beneficiaryservice;

@Autowired
private PersonService personservice;
    
@GetMapping("/register")    
public String registerBeneficiary(){


return "beneficiary_form.html";

} 

@PostMapping("/form")
public String formBeneficiary(@RequestParam(required=false) String username,
        @RequestParam(required=false) String password, @RequestParam(required=false) String password2, @RequestParam(required=false) String email, 
        @RequestParam(required=false) String name,@RequestParam String institution_type, 
        @RequestParam(required=false,defaultValue="0") Integer voluntary,@RequestParam(required=false,defaultValue="0") Integer toys, 
        @RequestParam(required=false,defaultValue="0") Integer clothing, @RequestParam(required=false,defaultValue="0") Integer food, 
        @RequestParam(required=false,defaultValue="0") Integer monetary_aid,@RequestParam(required=false,defaultValue="0") Integer school_supplies, 
        @RequestParam(required=false,defaultValue="0") Integer books,@RequestParam(required=false,defaultValue="0") Integer medical_supplies, 
        @RequestParam(required=false,defaultValue="0") Integer furnitures,@RequestParam(required=false,defaultValue="0") Integer legacies, 
        ModelMap model){

    try {   
        String user_type = "b";
        personservice.checkPassword(password, password2);
        personservice.createPerson(username, password, email,user_type );
        beneficiaryservice.createBeneficiary(username, name, institution_type, voluntary, 
                toys, clothing, food, monetary_aid, school_supplies, books, medical_supplies, furnitures, legacies);
        
        model.put("success", "Su usuario fue registrado correctamente");
        
    } catch (MyException ex) {
        model.put("error", ex.getMessage());
        return "beneficiary_form.html";
    }

return "index.html";

}







}
