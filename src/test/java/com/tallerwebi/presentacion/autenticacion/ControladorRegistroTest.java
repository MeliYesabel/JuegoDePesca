package com.tallerwebi.presentacion.autenticacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControladorRegistroTest {

    private final String email = "jesi@gmail.com";
    private final String password = "kitty1!";

    private ControladorRegistro controladorRegistro = new ControladorRegistro();

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

    /*
    @Test
    public void dadoQueNoExisteUnUsuarioQueSePuedaRegistrar(){
        dadoQueNoExisteJugadorRegistrado();

        ModelAndView vistaModelada = cuandoUnJugadorIngresaARegistro();

        entoncesSeMuestraRegistroExitosamente(vistaModelada);
    }


    private void entoncesSeMuestraRegistroExitosamente(ModelAndView vistaModelada) {
        assertThat(vistaModelada.getViewName(), equalToIgnoringCase("registro"));
    }
    private ModelAndView cuandoUnJugadorIngresaARegistro() {
        ControladorRegistro controladorRegistro = new ControladorRegistro();
        ModelAndView vistaModelada = controladorRegistro.irARegistro();
        return vistaModelada;
    }
    private void dadoQueNoExisteJugadorRegistrado() {}

     */

}
