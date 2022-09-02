package com.EggNews.Servicios;

import com.EggNews.Entidades.Noticia;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicios {
    
    public void crearNoticia(String titulo, String resumen, String cuerpo, String imgen){
     
        Noticia noticia= new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setResumen(resumen);
        noticia.setCuerpo(cuerpo);
        noticia.setFechaNoticia(new Date());
        noticia.setImagen(imgen);
        
        
    }
    
}
