
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
 @Autowired
 BeneficiaryService beneficiaryservice;
 
 @Autowired
 DonorService donorservice;
 
 @GetMapping("/dashboard")   
  public String AdminPanel(){
  
  return "panel.html";
  
  }

@GetMapping("/listDonors")   
    
  public String ListDonors(ModelMap model){
  model.addAttribute("donors",donorservice.ListDonor()); 
  return "list_donor.html";
  
  }

@GetMapping("/listBeneficiary")   
    
  public String ListBeneficiaries(ModelMap model){
   model.addAttribute("beneficiaries",beneficiaryservice.ListBeneficiary()); 
  return "list_beneficiary.html";
  
  }


  
    
}
