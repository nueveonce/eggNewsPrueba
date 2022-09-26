package com.EggNews.Controladores;

import com.EggNews.Entidades.Usuario;
import com.EggNews.Respositorios.UsuarioRepositorio;
import com.EggNews.Servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
