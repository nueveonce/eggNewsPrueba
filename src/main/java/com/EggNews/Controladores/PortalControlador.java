package com.EggNews.Controladores;

import com.EggNews.Entidades.Noticia;
import com.EggNews.Entidades.Usuario;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Servicios.NoticiaServicios;
import com.EggNews.Servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private NoticiaServicios notServ;
    @Autowired
    private UsuarioServicio usuarioServ;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/")
    public String index(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        List<Noticia> noticia = notServ.listarNoticias();

        modelo.addAttribute("noticia", noticia);
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "index.html";
        } else {
            return "index.html";
        }

    }

    @GetMapping("/registrar")
    public String retistrar() {
        return "registrar";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo, MultipartFile archivo) {

        try {
            usuarioServ.registrar(archivo, nombre, email, password, password2);
            modelo.put("exito", "Usuario resitrado exitosamente");
            return "redirect:/";

        } catch (MiException ex) {
            //Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registrar.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "USUARIO o contraseña invalidos");
        }
        return "/login";
    }

}
