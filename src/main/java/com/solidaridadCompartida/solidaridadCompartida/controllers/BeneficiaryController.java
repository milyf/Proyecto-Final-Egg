package com.solidaridadCompartida.solidaridadCompartida.controllers;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.entity.Post;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.ImageService;
import com.solidaridadCompartida.solidaridadCompartida.service.PersonService;
import com.solidaridadCompartida.solidaridadCompartida.service.PostService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryservice;
    @Autowired
    private PersonService personservice;
    @Autowired
    private ImageService imageservice;
    @Autowired 
    private PostService postservice;

    @GetMapping("/profile/{id}")
    public String profileBeneficiary(ModelMap model, @PathVariable String id, HttpSession session) {

        Beneficiary beneficiary = beneficiaryservice.getOne(id);
        String content = imageservice.getImgData(beneficiary.getImage().getContent());
        model.addAttribute("beneficiary", beneficiary);
        model.addAttribute("content", content);
        model.addAttribute("name", beneficiary.getName());
        model.addAttribute("institution_type", beneficiary.getInstitution_type());
        model.addAttribute("email", beneficiary.getEmail());
        model.addAttribute("requirements", beneficiaryservice.requirementsBeneficiary(beneficiary));
        return "profile_beneficiary.html";

    }
    
        @GetMapping("/post")
    public String postBeneficiary(ModelMap model, HttpSession session) {
        
        Person person = (Person) session.getAttribute("personsession"); 
        Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
        List<Post> posts = postservice.getBeneficiaryPosts(beneficiary);
        model.addAttribute("beneficiary", beneficiary);
        model.addAttribute("posts",posts);
        
        return "beneficiary_show_posts.html";

    }
    
    @GetMapping("/post/new")
    public String newPost(ModelMap model,HttpSession session){
            Person person = (Person) session.getAttribute("personsession"); 
            Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
            model.addAttribute("beneficiary",beneficiary);
        
    return "new_post_beneficiary.html";
    }
    
    @PostMapping("/post/new/submit")
    public String createPost(ModelMap model,HttpSession session, 
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body,MultipartFile file){
        Person person = (Person) session.getAttribute("personsession"); 
        Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
        Post post;
        try {
            post = postservice.createPost(title, body, beneficiary, file);
            //beneficiaryservice.addPost(beneficiary.getId(), post);
            return "redirect:/beneficiary/post?postNewSuccess=True";
        } catch (MyException ex) {
            model.addAttribute("title", title);
            model.addAttribute("body", body);
            model.put("error",ex.getMessage());
            return "new_post_beneficiary.html";
        }
        
    }

    @GetMapping("/register")
    public String registerBeneficiary(ModelMap modelo) {

        /*List<String> institutions_types= beneficiaryservice.ListInstitutionsTypes() ;

 modelo.addAttribute("institutions_types",institutions_types );*/
        return "beneficiary_form.html";

    }

    @PostMapping("/form")
    public String formBeneficiary(
            @RequestParam(required = false) String password, @RequestParam(required = false) String password2, @RequestParam(required = false) String email,
            @RequestParam(required = false) String name, @RequestParam(required = false) String institution_type,
            @RequestParam(required = false, defaultValue = "0") Integer voluntary, @RequestParam(required = false, defaultValue = "0") Integer toys,
            @RequestParam(required = false, defaultValue = "0") Integer clothing, @RequestParam(required = false, defaultValue = "0") Integer food,
            @RequestParam(required = false, defaultValue = "0") Integer monetary_aid, @RequestParam(required = false, defaultValue = "0") Integer school_supplies,
            @RequestParam(required = false, defaultValue = "0") Integer books, @RequestParam(required = false, defaultValue = "0") Integer medical_supplies,
            @RequestParam(required = false, defaultValue = "0") Integer furnitures, @RequestParam(required = false, defaultValue = "0") Integer legacies,
            ModelMap model) {

        try {
            beneficiaryservice.createBeneficiary(email, password, password2, name, institution_type, voluntary,
                    toys, clothing, food, monetary_aid, school_supplies, books, medical_supplies, furnitures, legacies);

            model.put("success", "Su usuario fue registrado correctamente");

        } catch (MyException ex) {
            model.put("error", ex.getMessage());
            model.put("emai", email);
            model.put("password", password);
            model.put("password2", password2);
            model.put("name", name);

            return "beneficiary_form.html";
        }

        return "redirect:/?success=true";

    }

    @PreAuthorize("hasAnyRole('ROLE_BENEFICIARY')")
    @GetMapping("/update")
    public String updateBeneficiary(ModelMap model, HttpSession session) {

        Person person = (Person) session.getAttribute("personsession"); 
        Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
        model.addAttribute("beneficiary", beneficiary);
        return "edit_beneficiary.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_BENEFICIARY')")
    @PostMapping("/update/form/{id}")
    public String updateFormBeneficiary(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String institution_type,
            @RequestParam(required = false, defaultValue = "0") Integer voluntary,
            @RequestParam(required = false, defaultValue = "0") Integer toys,
            @RequestParam(required = false, defaultValue = "0") Integer clothing,
            @RequestParam(required = false, defaultValue = "0") Integer food,
            @RequestParam(required = false, defaultValue = "0") Integer monetary_aid,
            @RequestParam(required = false, defaultValue = "0") Integer school_supplies,
            @RequestParam(required = false, defaultValue = "0") Integer books,
            @RequestParam(required = false, defaultValue = "0") Integer medical_supplies,
            @RequestParam(required = false, defaultValue = "0") Integer furnitures,
            @RequestParam(required = false, defaultValue = "0") Integer legacies,
            ModelMap model, MultipartFile file,HttpSession session) {
        Person person = (Person) session.getAttribute("personsession"); 
        Beneficiary beneficiary = beneficiaryservice.getOne(person.getId());
        try {
            beneficiaryservice.modifyBeneficiary(file, beneficiary.getEmail(),beneficiary.getPassword(), name,
                    voluntary, toys, clothing, food, monetary_aid, school_supplies,
                    books, medical_supplies, furnitures, legacies);

            model.put("success", "Su usuario fue actualizado correctamente");

        } catch (MyException ex) {

            model.put("error", ex.getMessage());
           
            model.put("name", name);

            return "/update/form?error=true";
        }

        return "redirect:/indexB?success=true";

    }

}
