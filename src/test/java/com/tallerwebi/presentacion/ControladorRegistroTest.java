package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    private final String email = "kitty@gmail.com";
    private final String password = "kitty1!";

    private ServicioUsuarioAuthI servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuarioI);

    @BeforeEach
    public void init(){
        servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
        controladorRegistro = new ControladorRegistro(servicioUsuarioI);
    }


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
        String vistaEsperada = "registro";
        String vistaObtenida = vistaModeladaGenerada.getViewName();

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertTrue(vistaModeladaGenerada.getModel().containsKey("usuarioDto"));
    }


    @Test
    public void dadoQueNoExisteUnUsuarioQueSePuedaRegistrarExitosamente(){
        dadoQueNoExisteJugadorRegistrado();

        ModelAndView vistaModelada = cuandoUnJugadorSeRegistra(email, password, password);

        entoncesSeMuestraRegistroExitosamente(vistaModelada);
    }

    private void entoncesSeMuestraRegistroExitosamente(ModelAndView vistaModelada) {
        String vistaObtenida = vistaModelada.getViewName();
        String vistaEsperada = "login-usuario";

        String mensajeExitoso = vistaModelada.getModel().get("mensaje").toString();
        String mensajeExitosoEsperado = "El usuario se registro exitosamente.";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeExitoso, equalTo(mensajeExitosoEsperado));
    }

    private void dadoQueNoExisteJugadorRegistrado() {
    }

    private ModelAndView cuandoUnJugadorSeRegistra(String email, String password, String password_repetida){
        ModelAndView vistaModelada = controladorRegistro.registrarUsuario(new UsuarioDto(email, password, password_repetida));
        return vistaModelada;
    }
}
