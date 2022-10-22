
package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.AdminService;
import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
 @Autowired
 BeneficiaryService beneficiaryservice;
 
 @Autowired
 DonorService donorservice;
 
 @Autowired 
 AdminService adminservice;
 
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

@GetMapping("/hardDeleteDonor/{email}")
public String hardDeleteDonorByEmail(@PathVariable String email){
    try {
    adminservice.HardDeleteUserByEmail(email);
    } catch (UsernameNotFoundException ex){
    return "redirect:/admin/listDonors?errorNotFound=true"; 
    }
    
    return "redirect:/admin/listDonors?success=True";
}

@GetMapping("/modifyDonor/{email}")
public String changeDonor(@PathVariable String email, ModelMap model){
    try {
    Donor donor = adminservice.GetDonor(email);
    
    model.addAttribute("name", donor.getName());
    model.addAttribute("donor_type", donor.getDonor_type());
    model.addAttribute("voluntary", donor.getVoluntary());
    return "modify_donor.html";
    } catch (UsernameNotFoundException ex){
    return "redirect:/admin/listDonors?errorNotFound=true"; 
    }
  
}

@PostMapping("/modifyDonor/form")
public String modifyDonorForm(@RequestParam(required=false) String email,
         @RequestParam(required=false) String name, @RequestParam(required=false) String donor_type, 
         @RequestParam(required=false,defaultValue="0") Integer voluntary, ModelMap model){
         try{
         adminservice.modifyDonor(email, name, donor_type, voluntary);
         return "redirect:/admin/listDonors?modifySuccess=true";
         }catch (MyException ex){
         model.put("error", ex.getMessage());
         model.put("emai",email);
        model.put("name",name);
        return "redirect:/admin/modifyDonor/form";
         }
}
    
}
