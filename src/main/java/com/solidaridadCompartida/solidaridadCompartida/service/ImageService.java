
package com.solidaridadCompartida.solidaridadCompartida.service;

import com.solidaridadCompartida.solidaridadCompartida.entity.Image;
import com.solidaridadCompartida.solidaridadCompartida.excepciones.MyException;
import com.solidaridadCompartida.solidaridadCompartida.repository.ImageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws MyException {
        if (file != null) {
            try {
                Image image = new Image();
                image.setMime(file.getContentType());//traigo con multipart el tipo de archivo
                image.setName(file.getName());
                image.setContenido(file.getBytes());

                return imageRepository.save(image);

            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
        return null;
    }

    public Image updateImage(MultipartFile file, String idImage) throws MyException {

        if (file != null) {
            try {
                Image image = new Image();

                if (idImage != null) {
                    Optional<Image> respuesta = imageRepository.findById(idImage);
                    if (respuesta.isPresent()) {
                        image = respuesta.get();
                    }
                }

                image.setMime(file.getContentType());//traigo con multipart el tipo de archivo
                image.setName(file.getName());
                image.setContenido(file.getBytes());

                return imageRepository.save(image);

            } catch (Exception e) {
                System.err.println(e.getMessage());

            }
        }
        return null;

    }

}
