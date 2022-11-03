
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.BeneficiaryRepository;
import com.solidaridadCompartida.solidaridadCompartida.repository.ImageRepository;
import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    
    @Autowired
    ImageRepository imagerepository;
    
    @Autowired
    BeneficiaryRepository beneficiaryrepository;
    
    
    public Image Save(MultipartFile file) throws MyException{
    
    if(file!=null){
    try{
        Image image = new Image();
        
        image.setMime(file.getContentType());
        image.setName(file.getName());
        image.setContent(file.getBytes());
        
        return imagerepository.save(image);
        
    
    }catch(Exception e){
    
        System.err.println(e.getMessage());
    }
   
    }
    
    return null;
    
    
    
    }
    
    public Image SaveEmpty(){
    
    Image image = new Image();
    return imagerepository.save(image);
    
    }
    
    public Image update(MultipartFile file, String idImage) throws MyException{
    
     if(file!=null){
    try{
        Image image = new Image();
        
        if(idImage != null){
        Optional<Image> answer = imagerepository.findById(idImage); 
        
        if(answer.isPresent()){
        
        image = answer.get();
        
        }
        
        }
        
        image.setMime(file.getContentType());
        image.setName(file.getName());
        image.setContent(file.getBytes());
        
        return imagerepository.save(image);
    
    }catch(Exception e){
    
        System.err.println(e.getMessage());
    }
   
    }
    
    return null;
    
    
    
    
    }
    
    public Image saveUserDefault(){
    
    File file = new File("/main/resources/static/images/profile_default.png");
    
    
    if(file!=null){
    try{
        Image image = new Image();
        
        image.setMime(URLConnection.guessContentTypeFromName(file.getName()));
        image.setName(file.getName());
        image.setContent(Files.readAllBytes(file.toPath()));
        
        imagerepository.save(image);
        return image;
    
    }catch(Exception e){
    
        System.err.println(e.getMessage());
    }
   
    }
    
    return null;
    
    
    
    }
   
    public Image loadFromBeneficiary(Beneficiary beneficiary) throws MyException{
    if (beneficiary.getImage() == null){
    throw new MyException("La imagen es nula");
    }
    if (beneficiary.getImage().getContent() == null){
    throw new MyException("El contenido de la imagen es nulo");
    }
    Image img = beneficiary.getImage();
    return img;
    }
    
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    } 

        
    }

