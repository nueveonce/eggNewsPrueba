package com.EggNews.enumeraciones;


public enum Rol {
    USER,
    PERIODISTA,
    ADMIN;    
    
    public static Rol conversion(String tipo_usuario){
        if (USER.name().equalsIgnoreCase(tipo_usuario)) {
            return USER;
        }else if (ADMIN.name().equalsIgnoreCase(tipo_usuario)) {
            return ADMIN;
        } else {
            return PERIODISTA;
        }
    }
}
