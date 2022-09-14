package com.EggNews.Controladores;

import com.EggNews.Entidades.Noticia;
import com.EggNews.Servicios.NoticiaServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

     @Autowired
    private NoticiaServicios notServ;
    
    @GetMapping("/")
    public String index(ModelMap modelo) {
         List<Noticia> noticia= notServ.listarNoticias();
         
         modelo.addAttribute("noticia",noticia);
        return "index.html";
    }

}
