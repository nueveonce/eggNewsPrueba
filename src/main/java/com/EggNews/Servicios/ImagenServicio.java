
package com.EggNews.Servicios;

import com.EggNews.Entidades.Imagen;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Respositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenrepositorio;
    
    public Imagen guardar(MultipartFile archivo) throws MiException{
        if (archivo!=null) {
            try {
                Imagen imagen= new Imagen();
                
                imagen.setMine(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenrepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
        }
        return null;
    }
    
    public Imagen actualizar(MultipartFile archivo, String idImagen) throws MiException{
        
          if (archivo!=null) {
            try {
                Imagen imagen= new Imagen();
                if (idImagen!=null) {
                    Optional<Imagen> respuesta = imagenrepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                        
                    }
                    
                }
                imagen.setMine(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenrepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            
        }
        return null;
        
        
    }
}
