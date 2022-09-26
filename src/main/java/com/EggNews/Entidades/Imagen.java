package com.EggNews.Entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid") // el valor del ID se va a generar de manera automatica.
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String mine; // asigna el formato del archivo de la imagen
    private String nombre;
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;  

    public Imagen() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
}
