package com.EggNews.Respositorios;

import com.EggNews.Entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo =  : titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT n FROM noticia n WHERE n.fechaNoticia = :fecha")
    public List<Noticia> buscarPorFecha(@Param("fechaNoticia") String  fecha);
        
    

}
