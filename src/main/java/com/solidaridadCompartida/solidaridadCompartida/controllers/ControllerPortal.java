package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.ImageService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ControllerPortal {
    
@Autowired
ImageService imageservice;

@Autowired
BeneficiaryService beneficiaryservice;

    @GetMapping("/")
    public String index() {

        return "index.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_DONOR')")
    @GetMapping("/indexD")
    public String index_D() {

        return "index_D.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_BENEFICIARY')")
    @GetMapping("/indexB")
    public String index_B(ModelMap model, HttpSession session) {
        Person person = (Person) session.getAttribute("personsession"); 
        Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
           
        String content= imageservice.getImgData(beneficiary.getImage().getContent());
        model.addAttribute("id", beneficiary.getId());
        model.addAttribute("content",content);
        model.addAttribute("beneficiary",beneficiary);
        

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
