package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorLoginUsuarioTest {
    //mockito
    private ServicioUsuarioAuthI servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
    private ControladorLogin controladorLogin = new ControladorLogin(servicioUsuarioI);
    private final String emailIngresado = "jesi@mail.com";
    private final String contraseniaIngresada = "Jesi1234!";

    @Test
    public void queSeGenereFormularioLoginParaQueSePuedaLoguearUsuario() {
        givenQueNoExisteFormularioLogIn();
        ModelAndView mav = whenSeCreaElFormularioLogIn();
        thenSePuedeLoguearUsuario(mav);
    }

    @Test
    public void siNoHayEmailQueElLoginFalle(){
        givenNoHayUsuarioLogueado();
        ModelAndView vistaModelada = whenGeneroUsuarioLogueado("", contraseniaIngresada);
        thenElLoginFallaPorEmail(vistaModelada);
    }

    @Test
    public void siNoHayContraseniaQueElLoginFalle(){
        givenNoHayUsuarioLogueado();
        ModelAndView vistaModelada = whenGeneroUsuarioLogueado(emailIngresado,"" );
        thenElLoginFallaPorPassword(vistaModelada);
    }

    /*
    validacion formato mail por defecto:
    debe tener un unico @
    debe tener minimo 3 letras despues del @ y antes del mismo
    */
    @Test
    public void siHayFormatoDeEmailInvalidoQueFalle(){
        givenNoHayUsuarioLogueado();
        ModelAndView mav = whenGeneroUsuarioLogueado("jesi@", contraseniaIngresada);
        thenElLoginFallePorMailInvalido(mav);

    }

    @Test
    public void dadoUnEmailInexistenteElLoginFalla(){
        givenNoHayUsuarioLogueado();

        //setear comportamiento de mockito
        doThrow(UsuarioInexistenteException.class).when(servicioUsuarioI).autenticar("bel@mail.com", "hola");
        /*ModelAndView mav = whenGeneroUsuarioLogueado("bel@mail.com", "hola");
        thenElLoginFalla(mav);*/
    }

    private void thenElLoginFalla(ModelAndView mav) {
        String vistaEsperada = "login-usuario";
        String vistaObtenida = mav.getViewName();

        String mensajeDeErrorObtenido = mav.getModel().get("error_not_exist").toString();
        String mensajeDeErrorEsperado = "El usuario no se encuentra registrado.";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));
    }

    private void thenElLoginFallePorMailInvalido(ModelAndView mav) {
        String vistaEsperada = "login-usuario";
        String vistaObtenida = mav.getViewName();

        String mensajeDeErrorObtenido = mav.getModel().get("error_formato_email").toString();
        String mensajeDeErrorEsperado = "El formato del email es invalido";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));

    }

    private void thenElLoginFallaPorEmail(ModelAndView vistaModelada) {
        String vistaObtenida = vistaModelada.getViewName();
        String vistaEsperada = "login-usuario";

        String mensajeDeErrorObtenido = vistaModelada.getModel().get("error_email_vacio").toString();
        String mensajeDeErrorEsperado = "El email es obligatorio";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));
    }

    private ModelAndView whenGeneroUsuarioLogueado(String emailIngresado, String contraseniaIngresada) {
        UsuarioDto usuarioIngresado = new UsuarioDto(emailIngresado, contraseniaIngresada);
        ModelAndView vistaModelada = controladorLogin.procesarLogin(usuarioIngresado, mock(HttpServletRequest.class));
        return vistaModelada;
    }

    private void givenNoHayUsuarioLogueado() {
    }

    private void thenElLoginFallaPorPassword(ModelAndView vistaModelada){
        String vistaEsperada = "login-usuario";
        String vistaObtenida = vistaModelada.getViewName();

        String mensajeDeErrorObtenido = vistaModelada.getModel().get("error_password_vacia").toString();
        String mensajeDeErrorEsperado = "La contraseña es obligatorio";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));


    }

    private void givenQueNoExisteFormularioLogIn() {
    }

    private ModelAndView whenSeCreaElFormularioLogIn() {
        return controladorLogin.mostrarLogin();
    }

    private void thenSePuedeLoguearUsuario(ModelAndView mav) {
        String vistaEsperada = "login-usuario";
        String vistaObtenida = mav.getViewName();
        assertThat(vistaObtenida, equalTo(vistaEsperada));
        assertTrue(mav.getModel().containsKey("usuarioDto"));
    }
}
