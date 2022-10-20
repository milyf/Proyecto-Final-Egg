
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerPortal {
  
  @GetMapping("/")  
  public String index(){
  
  return "index.html";
  
  } 
  
@PreAuthorize("hasAnyRole('ROLE_DONOR')")
@GetMapping("/indexD")
public String index_D(){

return "index_D.html";

} 


@PreAuthorize("hasAnyRole('ROLE_BENEFICIARY')")
@GetMapping("/indexB")
public String index_B(){

return "index_B.html";

} 

}

/*  
@GetMapping("/register")
public String registerBeneficiary(){
return "beneficiary_form.html";

}

@PostMapping("/beneficiary/form")

@GetMapping("/register")
public String registerDonor(){
return "donor_form.html";

}

@GetMapping("/")    
public String loginBeneficiary (){

return "login_form.html";

}

*/

  
    

