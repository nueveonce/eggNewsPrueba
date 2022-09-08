package com.EggNews.Controladores;

import com.EggNews.Excepciones.MiException;
import com.EggNews.Servicios.NoticiaServicios;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String altanoticia(@RequestParam String titulo_noticia, @RequestParam String resumen_noticia, @RequestParam String cuerpo, ModelMap modelo) {
        try {
            notServ.crearNoticia(titulo_noticia, resumen_noticia, cuerpo);
            modelo.put("exito", "El libro fue cargado correctamente"); //(llave, valor)
        } catch (MiException ex) {
            //Logger.getLogger(NoticiaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
        return "alta_noticia.html";
        }
        return "index.html";
    }

}
