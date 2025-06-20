package com.tallerwebi.dominio.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Clase estatica para el hasheo y deshasheo de la contrasenia (tirando a ser como la clase Math)
public class PasswordUtil {
    private static final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

    public static String hashear(String password) {
        return codificador.encode(password);
    }

    public static boolean verificar(String contraseniaIngresadoLogin, String contraseniaHasheadaBD){
        return codificador.matches(contraseniaIngresadoLogin, contraseniaHasheadaBD);
    }
}
