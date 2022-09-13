package com.EggNews.Entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    @Id
    @GeneratedValue(generator = "uuid") // el valor del ID se va a generar de manera automatica.
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id_noticia;
    private String titulo;
    private String resumen;
    private String cuerpo;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_noticia;
    private String imagen;

    public Noticia() {
    }

    public String getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(String id_noticia) {
        this.id_noticia = id_noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha_noticia() {
        return fecha_noticia;
    }

    public void setFecha_noticia(Date fecha_noticia) {
        this.fecha_noticia = fecha_noticia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Noticia{" + "id_noticia=" + id_noticia + ", titulo=" + titulo + ", resumen=" + resumen + ", cuerpo=" + cuerpo + ", fecha_noticia=" + fecha_noticia + ", imagen=" + imagen + '}';
    }

 
    
}