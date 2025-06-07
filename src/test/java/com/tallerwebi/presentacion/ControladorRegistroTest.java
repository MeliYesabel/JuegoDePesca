package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class ControladorRegistroTest {

    private final String email = "kitty@gmail.com";
    private final String password = "kitty1!";

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

}
