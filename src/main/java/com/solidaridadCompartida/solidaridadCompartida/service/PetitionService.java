package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Donor;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.entity.Petition;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import com.solidaridadCompartida.solidaridadCompartida.repository.DonorRepository;
import com.solidaridadCompartida.solidaridadCompartida.repository.PetitionRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private DonorRepository donorRepository;

    
     @Autowired
    private ImageService imageServ;
     
     
    @Transactional
    public void createPetition(String id, Boolean voluntary, String voluntaryType,
            String petitionType, MultipartFile file) throws MyException {

        Beneficiary beneficiary = beneficiaryRepository.findById(id).get();

        validatePetition(beneficiary, voluntary, voluntaryType, petitionType);

        Petition petition = new Petition();

        petition.setBeneficiary(beneficiary);
        petition.setDonor(null);
        petition.setFechaAlta(new Date());
        petition.setAceptation(Boolean.FALSE);
        petition.setAlta(Boolean.TRUE);
        petition.setVoluntary(voluntary);
        petition.setVoluntaryType(voluntaryType);
        petition.setPetitionType(petitionType);
        
        //ACA GUARDAR ALGUNA IMAGEN QUE SEA NORMALIZADA ´PARA TODOS
        Image image=imageServ.saveImage(file);
        petition.setImage(image);
        //petition.setImage(image); CARGAR UNA IMAGEN QUE PUESA SER NEUTRAL DESDE EL PRINCIPIO

        petitionRepository.save(petition);

    }

    private void validatePetition(Beneficiary beneficiary, Boolean voluntary, String voluntaryType,
            String petitionType) throws MyException {

        if (beneficiary == null) {
            throw new MyException("El beneficiario no puede ser nulo o estar vacío");
        }

        if (voluntary == false && (petitionType.isEmpty() || petitionType == null)) {
            throw new MyException("Tu peticion no puede estar vacia. Contanos qué necesitas");
        }

        if (voluntary == true && (voluntaryType.isEmpty() || voluntaryType == null)) {
            throw new MyException("Por favor indique qué tipo de Voluntariado requiere");
        }

    }

    private List<Petition> listPetitions() {

        List<Petition> petitions = new ArrayList();
        petitions = petitionRepository.findAll();

        return petitions;

    }

    public Petition getOne(String id) {
        return petitionRepository.getOne(id);
    }

    @Transactional
    public void updatePetition(String id, Beneficiary beneficiary, Boolean voluntary, String voluntaryType,
            String petitionType, Boolean alta,  MultipartFile file, String idImage) throws MyException {

        validatePetition(beneficiary, voluntary, voluntaryType, petitionType);

        Optional<Petition> respuestaPet = petitionRepository.findById(id);

        if (respuestaPet.isPresent()) {
            
            Petition petition = respuestaPet.get();
            
            if (petition.getDonor()==null){
             petition.setVoluntary(voluntary);
            petition.setVoluntaryType(voluntaryType);
            petition.setPetitionType(petitionType);
            petitionRepository.save(petition);   
              
            }else{
               
           // String idImage = null;
            if (petition.getImage() != null) {
                idImage = petition.getImage().getIdImage();
            }

            Image image = imageServ.updateImage(file, idImage);
            petition.setImage(image);
               
            }
            
        }

    }
    
    @Transactional
    public void aceptationDonor(String id, Boolean alta, MultipartFile file, Donor donor, 
            Boolean aceptation) throws MyException{
        
        Optional <Petition> respuestaPet=petitionRepository.findById(id);
        if (respuestaPet.isPresent()){
            Petition petition=respuestaPet.get();
            
            petition.setDonor(donor);
            petition.setAceptation(Boolean.TRUE);
                        
            petitionRepository.save(petition);
        }
        
    }
    

    //click aceptar del beneficiario desencadena esto
    public void matchDonor(String id, Donor donor) {

        Optional<Petition> respuestaPet = petitionRepository.findById(id);
        if (respuestaPet.isPresent()) {
            Petition petition = respuestaPet.get();
            petition.setDonor(donor);
            petition.setAlta(Boolean.FALSE);

            petitionRepository.save(petition);

        }

    }

    @Transactional
    public void expiration(Petition petition) {
            String requestDate = petition.getFechaAlta().toString();
            LocalDate upDate = LocalDate.parse(requestDate);
            LocalDate currentDate = LocalDate.now();
            if (ChronoUnit.DAYS.between(upDate, currentDate) > 30) {
                petition.setAlta(Boolean.FALSE);
            }
            petitionRepository.save(petition);

    }

    public void activePetition(List<Petition> petitions) {
        for (Petition petition : petitions) {
            if (petition.getAlta() == true) {
               expiration(petition);
            }
        }
    }
    
        @Transactional
    public void softDeletePetition(String id) {
        Optional<Petition> respuestaPet = petitionRepository.findById(id);
        if (respuestaPet.isPresent()) {
            Petition petition = respuestaPet.get();
            petition.setAlta(Boolean.FALSE);
            petitionRepository.save(petition);
        }
    }
    
    
    
}



