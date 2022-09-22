package com.EggNews.Controladores;

import com.EggNews.Entidades.Noticia;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Servicios.NoticiaServicios;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticia") // localhost:8080/noticia
public class NoticiaControlador {

    @Autowired
    private NoticiaServicios notServ;

    @GetMapping("/altanoticia") //localhost:8080/noticia/altanoticia
    public String registrar() {
        return "alta_noticia.html";
    }

    @PostMapping("/altanoticia")
    public String altanoticia(@RequestParam(required = false) MultipartFile archivo, @RequestParam String titulo_noticia, @RequestParam String resumen_noticia, @RequestParam String cuerpo, ModelMap modelo) {
        try {
            notServ.crearNoticia(archivo,titulo_noticia, resumen_noticia, cuerpo);
            modelo.put("exito", "La noticia fue cargada correctamente"); //(llave, valor)
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "alta_noticia.html";
        }
        return "index.html";
    }

    @GetMapping("/modifnoticia/{id_noticia}") //localhost:8080/noticia/modfnoticia
    public String modifnoticia(@PathVariable String id_noticia, ModelMap modelo) {
        Noticia noticia = new Noticia();
        noticia = notServ.getOne(id_noticia);
        modelo.addAttribute(noticia);   
        

        return "modifnoticia.html";
    }
    /*
    @PostMapping("/modifnoticia/{id_noticia}")
    public String modifnoticia(MultipartFile archivo, @RequestParam String titulo_noticia, @RequestParam String resumen_noticia, @RequestParam String cuerpo, ModelMap modelo){
        try {
            notServ.modificarNoticia(archivo,titulo_noticia, resumen_noticia, cuerpo);
            return "completa.html";
        } catch (MiException ex) {
            Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "modifnoticia.html";
        }
    }
*/
    @GetMapping("/completa/{id_noticia}")
    public String completa(@PathVariable String id_noticia, ModelMap modelo) {

        Noticia noticia = new Noticia();
        noticia = notServ.getOne(id_noticia);
        modelo.addAttribute(noticia);        
        return "completa.html";
    }
    
   
}
