
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
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
@RequestMapping("/login")   
public class LoginController {
    
@Autowired PersonService personservice;

@GetMapping("/")    
public String loginBeneficiary (){

return "login_form.html";

}

    
 @PostMapping("/form")
 public String formLogin(@RequestParam(required=false) String username,@RequestParam(required=false) String password,  ModelMap model){
 
    try {
        personservice.loginPerson(username, password);
        model.put("sucess"," ");
    } catch (MyException ex) {
        model.put("error", ex.getMessage());
        return "login_form.html";
    }
    
 return "index.html" ;
 
 }





    
    
}
