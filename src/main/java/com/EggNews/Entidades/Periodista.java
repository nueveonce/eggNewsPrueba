
package com.EggNews.Entidades;

import java.util.ArrayList;
import javax.persistence.Entity;
@Entity
public class Periodista extends Usuario{
    
    private ArrayList<Noticia> cantidadNoticias;
    private Integer sueldoMensual;
}
