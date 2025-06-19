package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    private final String email = "kitty@gmail.com";
    private final String password = "kitty1!";

    private ServicioUsuarioAuthI servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
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


    @Test
    public void dadoQueNoExisteUnUsuarioQueSePuedaRegistrarExitosamente(){
        dadoQueNoExisteJugadorRegistrado();

        ModelAndView vistaModelada = cuandoUnJugadorSeRegistra(email, password, password);

        entoncesSeMuestraRegistroExitosamente(vistaModelada);
    }

    private void entoncesSeMuestraRegistroExitosamente(ModelAndView vistaModelada) {
    }

    private void dadoQueNoExisteJugadorRegistrado() {
    }

    private ModelAndView cuandoUnJugadorSeRegistra(String email, String password, String password_repetida){
        ModelAndView vistaModelada = controladorRegistro.registrarUsuario(new UsuarioDto(email, password, password_repetida));
        return vistaModelada;
    }
}
