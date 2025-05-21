package com.tallerwebi.dominio.autenticacion;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UserExistenteExcepcion;
import com.tallerwebi.presentacion.autenticacion.UsuarioDto;

public class ServicioRegistroTest {

    ServicioUsuarioI servicioUsuarioI = new ServicioUsuarioImpl();

    @Test
    public void dadoUnaContraseniaInvalidaQueElRegistroFalle(){
        givenQueNoHayRegistro();
        assertThrows(ContraseniaInvalidaExcepcion.class,()-> whenUnUsuarioSeRegistra(new UsuarioDto("belen@mail.com", "kitty", "kitty")));
    }


    private void whenUnUsuarioSeRegistra(UsuarioDto usuarioDto) {
        servicioUsuarioI.registrarUsuario(usuarioDto);
    }

    private void givenQueNoHayRegistro() {}

    @Test
    public void dadoUnMailRegistradoQueElRegistroFalle(){
        givenQueNoHayRegistro();
        assertThrows(UserExistenteExcepcion.class,()-> whenUnUsuarioSeRegistra(new UsuarioDto("jesi@mail.com", "hola", "hola")));
    }
}
