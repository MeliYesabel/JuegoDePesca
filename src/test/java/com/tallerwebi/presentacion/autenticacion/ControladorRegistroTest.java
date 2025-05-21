package com.tallerwebi.presentacion.autenticacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import com.tallerwebi.dominio.autenticacion.ServicioUsuarioI;
import com.tallerwebi.dominio.excepcion.ContraseniaInvalidaExcepcion;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    private final String email = "belen@gmail.com";
    private final String password = "Kitty12!";

    private ServicioUsuarioI servicioUsuarioI = mock(ServicioUsuarioI.class);
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuarioI);

    @Test
    public void queSePuedaCrearFormularioRegistroParaCrearUsuario() {
        givenQueNoExisteFormularioRegistro();
        ModelAndView vistaModeladaGenerada = whenSeCreaElFormularioRegistro();
        thenPuedoCrearUsuario(vistaModeladaGenerada);


    }

    private void thenPuedoCrearUsuario(ModelAndView vistaModeladaGenerada) {
        String vistaEsperada = "registro";
        String vistaObtenida = vistaModeladaGenerada.getViewName();

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertTrue(vistaModeladaGenerada.getModel().containsKey("usuarioDto"));
    }

    private ModelAndView whenSeCreaElFormularioRegistro() {
        return controladorRegistro.mostrarRegistro();
    }

    private void givenQueNoExisteFormularioRegistro() {}

    
    @Test
    public void dadoQueNoExisteUnUsuarioQueSePuedaRegistrarExitosamente(){
        dadoQueNoExisteJugadorRegistrado();

        ModelAndView vistaModelada = cuandoUnJugadorSeRegistra(email, password, password);

        entoncesSeMuestraRegistroExitosamente(vistaModelada);
    }


    private void entoncesSeMuestraRegistroExitosamente(ModelAndView vistaModelada) {
        String vistaObtenida = vistaModelada.getViewName();
        String vistaEsperada = "login-pescador";

        String mensajeExitoso = vistaModelada.getModel().get("mensaje").toString();
        String mensajeExitosoEsperado = "El usuario se registro exitosamente.";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeExitoso, equalTo(mensajeExitosoEsperado));
    }

    private ModelAndView cuandoUnJugadorSeRegistra(String email, String password, String pswdRepetida) {
        ModelAndView vistaModelada = controladorRegistro.registrarUsuario(new UsuarioDto(email, password, pswdRepetida));
        return vistaModelada;
    }

    private void dadoQueNoExisteJugadorRegistrado() {}
}
