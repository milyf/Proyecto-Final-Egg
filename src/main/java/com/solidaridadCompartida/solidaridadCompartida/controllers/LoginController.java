
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.PersonService;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/login")   
public class LoginController {
    
@Autowired PersonService personservice;

@GetMapping("")    
public String login (@RequestParam(required=false, value="error",defaultValue = "false") boolean error, ModelMap model){
    
  if(error){
  
  model.put("error", "Usuario o Contraseña invalidos");

  }  

return "login_form.html";

}


   
 /*@PostMapping("/form")
 public String formLogin(@RequestParam(required=false) String email,@RequestParam(required=false) String password,  ModelMap model){
 
    try {
        personservice.loginPerson(email, password);
        model.put("sucess"," ");
    } catch (MyException ex) {
        model.put("error", ex.getMessage());
        return "login_form.html";
    }
    
 return "index.html" ;
 
 } */

 
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_DONOR")) {
            return "redirect:/indexD";
        }else if (request.isUserInRole("ROLE_BENEFICIARY")){
        
         return "redirect:/indexB";
        }else if (request.isUserInRole("ROLE_ADMIN")){
        return "redirect:/admin/dashboard";
        
        }else{
        
        return "redirect:/index";
        
        }
       
    }

 
 




 }   
    

