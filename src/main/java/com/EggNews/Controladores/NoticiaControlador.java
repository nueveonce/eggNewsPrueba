package com.EggNews.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia") // localhost:8080/noticia
public class NoticiaControlador {

    @GetMapping("/altanoticia") //localhost:8080/noticia/altanoticia
    public String registrar() {
        return "alta_noticia.html";
    }

    @PostMapping("/altanoticia")
    public String alta(@RequestParam String titulo_noticia) {
        System.out.println("TITULO NOTICIA: " + titulo_noticia);
        return "alta_noticia.html";
    }

}
