package com.EggNews.Controladores;

import com.EggNews.Entidades.Usuario;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Respositorios.UsuarioRepositorio;
import com.EggNews.Servicios.UsuarioServicio;
import java.util.List;
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
@RequestMapping("/admin")// localhots:8080/admin
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuarios")
    public String ListarUsuarios(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listar();
        modelo.addAttribute("usuarios", usuarios);
        return "lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        usuarioServicio.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id) {
        Usuario usuario = new Usuario();
        usuario = usuarioRepositorio.getById(id);
        modelo.addAttribute("usuario", usuario);
        return "modificar_usuario";
    }

    @PostMapping("/modificar_usuario/{id}")
    public String modificarUsuario(MultipartFile archivo, @PathVariable String id, @RequestParam String nombre, @RequestParam String email) {

        try {
            usuarioServicio.modificarUsuario(archivo, id, nombre, email);
            return "redirect:/admin/usuarios";
        } catch (MiException ex) {
            Logger.getLogger(AdminControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/admin/usuarios";
        }

    }
}
