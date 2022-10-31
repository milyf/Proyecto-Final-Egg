package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.entity.Person;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BeneficiaryService implements UserDetailsService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private ImageService imageservice;

    @Transactional
    public void createBeneficiary(String email, String password, String password2,
            String name, String institution_type, Integer voluntary, Integer toys,
            Integer clothing, Integer food, Integer monetary_aid,
            Integer school_supplies, Integer books, Integer medical_supplies,
            Integer furnitures, Integer legacies) throws MyException {

        validate(email, password, name);
        checkPassword(password, password2);

        Beneficiary beneficiary = new Beneficiary();

        beneficiary.setEmail(email);
        beneficiary.setPassword(new BCryptPasswordEncoder().encode(password));
        beneficiary.setName(name);
        beneficiary.setInstitution_type(institution_type);
        beneficiary.setVoluntary(voluntary);
        beneficiary.setToys(toys);
        beneficiary.setClothing(clothing);
        beneficiary.setFood(food);
        beneficiary.setMonetary_aid(monetary_aid);
        beneficiary.setSchool_supplies(school_supplies);
        beneficiary.setBooks(books);
        beneficiary.setMedical_supplies(medical_supplies);
        beneficiary.setFurnitures(furnitures);
        beneficiary.setLegacies(legacies);
        beneficiary.setRol(Rol.BENEFICIARY);
        beneficiary.setAlta(Boolean.TRUE);

        Image image = imageservice.saveUserDefault();

        beneficiary.setImage(image);

        beneficiaryRepository.save(beneficiary);

    }

    public List<Beneficiary> ListBeneficiary() {

        List<Beneficiary> beneficiaries = new ArrayList();

        beneficiaries = beneficiaryRepository.findAll();

        return beneficiaries;

    }

    public void modifyBeneficiary(MultipartFile file, String email, String password, String name, Integer voluntary, Integer toys, Integer clothing, Integer food, Integer monetary_aid, Integer school_supplies, Integer books, Integer medical_supplies, Integer furnitures, Integer legacies) throws MyException {

        validate(email, password, name);
        Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);

        if (response.isPresent()) {

            Beneficiary beneficiary = response.get();
            beneficiary.setName(name);
            beneficiary.setVoluntary(voluntary);
            beneficiary.setToys(toys);
            beneficiary.setClothing(clothing);
            beneficiary.setFood(food);
            beneficiary.setMonetary_aid(monetary_aid);
            beneficiary.setSchool_supplies(school_supplies);
            beneficiary.setBooks(books);
            beneficiary.setMedical_supplies(medical_supplies);
            beneficiary.setFurnitures(furnitures);
            beneficiary.setLegacies(legacies);

            String idImage = null;

            if (beneficiary.getImage() != null) {

                idImage = beneficiary.getImage().getId();

            }

            Image image = imageservice.update(file, idImage);
            beneficiary.setImage(image);

            beneficiaryRepository.save(beneficiary);

        } else {
            throw new MyException("");
        }

    }

    public Boolean validatePassword(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if (numberFlag && capitalFlag && lowerCaseFlag) {
                return true;
            }
        }
        return false;

    }

    private void validate(String email, String password, String name) throws MyException {

        if (name.isEmpty() || name == null) {

            throw new MyException("El nombre no puede ser nulo o estar vacío");
        }

        if (password.isEmpty() || password == null) {

            throw new MyException("La contraseña no puede ser nula o estar vacía");
        }

        if (email.isEmpty() || email == null) {

            throw new MyException("El email no puede ser nulo o estar vacío");
        }

        Boolean correctPassword;
        correctPassword = this.validatePassword(password);
        if (correctPassword == false) {

            throw new MyException("La contraseña debe tener al menos un número, un caracter en minúscula y uno en mayúsculas");

        }

    }

    public void checkPassword(String password, String password2) throws MyException {

        Boolean check = password.equals(password2);
        if (check == false) {

            throw new MyException("No coincide la contraseña");

        }

    }

    public void loginPerson(String email, String password) throws MyException {

        if (email.isEmpty() || email == null) {

            throw new MyException("El email no puede ser nulo o estar vacío");
        }

        if (password.isEmpty() || password == null) {

            throw new MyException("La contraseña no puede ser nula o estar vacía");
        }

        if (beneficiaryRepository.searchByEmail(email) == null) {

            throw new MyException("El email no está registrado");

        } else {

            Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);

            Beneficiary beneficiary = response.get();
            beneficiary.setEmail(email);
            beneficiary.setPassword(password);

            Boolean x = beneficiary.getPassword().equals(password);

            if (x == false) {

                throw new MyException("La contraseña ingresada no es correcta");
            }
        }

    }

    public List<String> ListInstitutionsTypes() {

        List<String> institutions_types = new ArrayList();

        institutions_types.add("Iglesias");
        institutions_types.add("Escuelas");
        institutions_types.add("Comedores");
        institutions_types.add("Merenderos");

        return institutions_types;

    }

    public Beneficiary getOne(String id) throws UsernameNotFoundException {

        Optional<Beneficiary> response = beneficiaryRepository.findById(id);

        if (response.isPresent()) {
            Beneficiary beneficiary = response.get();

            return beneficiary;

        } else {

            throw new UsernameNotFoundException("El usuario no existe");

        }
    }

    public List<String> requirementsBeneficiary(String id) {

        Beneficiary beneficiary = this.getOne(id);

        List<String> results = new ArrayList<String>();

        if (beneficiary.getVoluntary() == 1) {

            results.add("Voluntarios");
        }

        if (beneficiary.getToys() == 1) {

            results.add("Juguetes");
        }

        if (beneficiary.getClothing() == 1) {

            results.add("Prendas de vestir");
        }

        if (beneficiary.getFood() == 1) {

            results.add("Alimentos");
        }

        if (beneficiary.getMonetary_aid() == 1) {

            results.add("Ayuda economica");
        }

        if (beneficiary.getSchool_supplies() == 1) {

            results.add("Útiles escolares");
        }

        if (beneficiary.getBooks() == 1) {

            results.add("Libros");
        }

        if (beneficiary.getMedical_supplies() == 1) {

            results.add("Medicamentos");
        }

        if (beneficiary.getFurnitures() == 1) {

            results.add("Muebles");
        }

        if (beneficiary.getLegacies() == 1) {

            results.add("Legado");
        }

        return results;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Beneficiary> response = beneficiaryRepository.searchByEmail(email);

        if (response.isPresent()) {
            Beneficiary beneficiary = response.get();

            List<GrantedAuthority> permissions = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + beneficiary.getRol().toString());

            permissions.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("beneficiarysession", beneficiary);

            return new User(beneficiary.getEmail(), beneficiary.getPassword(), permissions);

        } else {

            throw new UsernameNotFoundException("No se pudo cargar el usuario");

        }
    }

}
