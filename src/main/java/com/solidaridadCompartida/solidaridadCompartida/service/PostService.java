package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Beneficiary;
import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.entity.Post;
import com.solidaridadCompartida.solidaridadCompartida.enumeracion.Rol;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.PostRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService implements UserDetailsService {

    @Autowired
    private ImageService imageservice;
    @Autowired
    private PostRepository postrepository;

    @Transactional
    public Post createPost(String title, String body, Beneficiary beneficiary, MultipartFile file) throws MyException {

        validate(title, body);

        Post post = new Post();

        post.setTitle(title);
        post.setBody(body);
        post.setBeneficiary(beneficiary);
        post.setAlta(Boolean.TRUE);

        if (file == null) {
        Image image = imageservice.SaveEmpty();   
        post.setImage(image);

        }
        
        else{
        
         Image image = imageservice.Save(file);
         post.setImage(image);
        }
       
        postrepository.save(post);
        return post;
    }

    private void validate(String title, String body) throws MyException {

        if (title.isEmpty() || title == null) {

            throw new MyException("El título no puede ser nulo o estar vacío");
        }

        if (body.isEmpty() || body == null) {

            throw new MyException("El cuerpo de la noticia no puede estar vacio");
        }

    }
    
    public List<Post> getBeneficiaryPosts(Beneficiary beneficiary){
        List<Post> posts = postrepository.searchByBEneficiaryId(beneficiary);
        return posts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
