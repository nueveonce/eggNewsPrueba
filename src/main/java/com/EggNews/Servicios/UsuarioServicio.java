package com.EggNews.Servicios;

import com.EggNews.Entidades.Imagen;
import com.EggNews.Entidades.Usuario;
import com.EggNews.Excepciones.MiException;
import com.EggNews.Respositorios.UsuarioRepositorio;
import com.EggNews.enumeraciones.Rol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String email, String password, String password2) throws MiException {

        validarDatos(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setFechaAlta(new Date());
        usuario.setRol(Rol.USER);
        Imagen imagen = imagenServicio.guardar(archivo);

        usuario.setImagen(imagen);

        usuarioRepositorio.save(usuario);

    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }

    @Transactional
    public void eliminar(String id) {
        usuarioRepositorio.deleteById(id);
    }

    public void validarDatos(String nombre, String email, String password, String password2) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("EL NOMBRE NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (email.isEmpty() || email == null) {
            throw new MiException("EL E-MAIL NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (password.isEmpty() || password == null || password.length() < 5) {
            throw new MiException("LA CONTRASEÑA NO DEBE ESTAR VACIA, Y DEBE TENER 6 CARACTERES COMO MINIMO");
        }

        if (!password.equals(password2)) {
            throw new MiException("LAS COTRASEÑAS INGRESADAS DEBEN SER IGUALES");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            /*atrapammos al usuario que inicio sesion y lo guardamos en la sesion web*/
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);

            return user;

        } else {
            return null;
        }
    }

    public void modificarUsuario(MultipartFile archivo, String id, String nombre, String email, String tipo_usuario) throws MiException {
        validarDatos2(id, nombre, email, tipo_usuario);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();            
            
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setRol(Rol.conversion(tipo_usuario));
            usuarioRepositorio.save(usuario);
        }
    }

    public void validarDatos2(String id, String nombre, String email, String tipo_usuario) throws MiException {

        if (id == null || id.isEmpty()) {
            throw new MiException("EL ID DE USUARIO NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("EL NOMBRE NO DEBE SER NULO NI ESTAR VACIO");
        }

        if (email.isEmpty() || email == null) {
            throw new MiException("EL E-MAIL NO DEBE SER NULO NI ESTAR VACIO");
        }
        if (tipo_usuario == null || tipo_usuario.isEmpty()) {
            throw new MiException("EL TIPO DE USUARIO NO PUEDE SER NULO NI ESTAR VACIO");
        }

//        if (archivo.isEmpty() || archivo == null) {
//            throw new MiException("EL ******* archivo ********** DE LA NOTICIA NO DEBE SER NULO NI ESTAR VACIO");
//        }
    }
}
