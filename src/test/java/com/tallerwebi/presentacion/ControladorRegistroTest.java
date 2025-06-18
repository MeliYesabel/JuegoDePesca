package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioI;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    private final String email = "kitty@gmail.com";
    private final String password = "kitty1!";

    private ServicioUsuarioI servicioUsuarioI = mock(ServicioUsuarioI.class);
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuarioI);

    @Test
    public void queSePuedaCrearFormularioRegistroParaCrearUsuario(){
        givenQueNoExisteFormularioRegistro();
        ModelAndView vistaModeladaGenerada = whenSeCreaElFormularioRegistro();
        thenPuedoCrearUsuario(vistaModeladaGenerada);
    }


    private void givenQueNoExisteFormularioRegistro() {
    }

    private ModelAndView whenSeCreaElFormularioRegistro() {
        return controladorRegistro.mostrarRegistro();
    }

    private void thenPuedoCrearUsuario(ModelAndView vistaModeladaGenerada) {
    }



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
        ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuarioI);
        ModelAndView vistaModelada = controladorRegistro.irARegistro();
        return vistaModelada;
    }
    private void dadoQueNoExisteJugadorRegistrado() {}
*/
}
