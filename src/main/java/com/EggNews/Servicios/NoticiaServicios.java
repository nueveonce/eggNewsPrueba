package com.EggNews.Servicios;

import com.EggNews.Entidades.Noticia;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Respositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicios {

    @Autowired/* indica al servidor que esta variable va a ser
    inicializada por el servidor(INYECCION DE DEPENDENCIA)    
     */

    private NoticiaRepositorio noticiaRepositorio;

    @Transactional/* si el metodo se ejecuta sin lanzar excepciones, 
    se realiza un commit a la base de datos, de lo contrario no se aplican 
    cambios en la base de datos y se vuelve atras (ROLLBACK)
    TODOS LOS METODOS QUE GENEREN MODIFICACIONES PERMANENTES EN LA BASE DE DATOS DEBEN SER
    ANOTADOS COMO TRANSACTIONAL
     */
    public void crearNoticia(String titulo, String resumen, String cuerpo/*, String imgen*/) throws MiException {
        validarDatos(titulo, resumen, cuerpo);
        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setResumen(resumen);
        noticia.setCuerpo(cuerpo);
        noticia.setFecha_noticia(new Date());
        //noticia.setImagen(imgen);

        noticiaRepositorio.save(noticia);

    }

    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();

        return noticias;
    }

    public Noticia getOne(String id) {
        return noticiaRepositorio.getOne(id);
    }

    @Transactional
    public void modificarNoticia(String titulo, String resumen, String cuerpo, /*String imgen,*/ String idNoticia) throws MiException {

        validarDatos(titulo, resumen, cuerpo);
        Optional<Noticia> respuesta = noticiaRepositorio.findById(idNoticia);

        if (respuesta.isPresent()) {

            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setResumen(resumen);
            noticia.setCuerpo(cuerpo);
            //noticia.setImagen(imgen);

            noticiaRepositorio.save(noticia);
        }
    }

    public void validarDatos(String titulo, String resumen, String cuerpo) throws MiException {

        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("EL TITULO DE LA NOTICIA NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (resumen.isEmpty() || resumen == null) {
            throw new MiException("EL RESUMEN DE LA NOTICIA NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("EL CUERPO DE LA NOTICIA NO DEBE SER NULO NI ESTAR VACIO");
        }

    }

}
