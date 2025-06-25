package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.excepcion.UsuarioInexistenteLoginException;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRecuperacionContraseniaTest {
    private ServicioUsuarioAuthI servicioUsuarioI = mock(ServicioUsuarioAuthI.class);
    private ControladorRecuperoContrasenia controladorRecuperoContrasenia = new ControladorRecuperoContrasenia(servicioUsuarioI);

    @Test
    public void queSePuedaCrearFormRecuperacionContrasenia() {
        givenQueNoExisteFormRecuperoContrasenia();
        ModelAndView mav = whenSeCreaElFormRecuperoContrasenia();
        thenSePuedeRecuperarContrasenia(mav);
    }

    @Test
    public void siNoHayMailQueLaRecuparacionFalle(){
        givenQueNoHayRecuperoContrasenia();
        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("");
        thenLaRecuperacionFalla(vistaModelada, "email_vacio", "El email es obligatorio", "recuperar-contrasenia");

    }

    @Test
    public void siNoHayMailRegistradoQueLaRecuperacionFalle(){
        givenQueNoHayRecuperoContrasenia();
        doThrow(UsuarioInexistenteLoginException.class).when(servicioUsuarioI).buscarUsuarioPorMail("belen@mail.com");

        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("belen@mail.com");
        thenLaRecuperacionFalla(vistaModelada, "no_existe", "El email ingresado no se encuentra registrado.", "recuperar-contrasenia");
    }

    @Test
    public void siHayMailRegistradoQueLaRecuperacionSeaExitosa(){
        givenQueNoHayRecuperoContrasenia();
        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("bart@gmail.com");
        thenLaRecuperacionEsExitosa(vistaModelada);
    }

    private void thenLaRecuperacionEsExitosa(ModelAndView vistaModelada) {
        String vistaEsperada = "login-pescador";
        String vistaObtenida = vistaModelada.getViewName();

        String mensajeObtenido = vistaModelada.getModel().get("mensaje").toString();
        String mensajeEsperado = "Se le envio un enlace de recuperacion a tu email.";

        assertThat(vistaObtenida, equalTo(vistaEsperada));
        assertThat(mensajeObtenido, equalTo(mensajeEsperado));
    }


    private void givenQueNoExisteFormRecuperoContrasenia() {
    }

    private ModelAndView whenSeCreaElFormRecuperoContrasenia(){
        return controladorRecuperoContrasenia.mostrarFormRecuperoContrasenia();
    }

    private void thenSePuedeRecuperarContrasenia(ModelAndView mav) {
        String vistaEsperada= "recuperar-contrasenia";
        String vistaObtenida= mav.getViewName();

        assertThat(vistaObtenida, equalTo(vistaEsperada));
    }


    private void thenLaRecuperacionFalla(ModelAndView vistaModelada, String keyEsperado, String msjErrorEsperado, String vistaEsperada) {
        String vistaObtenida = vistaModelada.getViewName();
        String mensajeDeErrorObtenido = vistaModelada.getModel().get(keyEsperado).toString();

        assertThat(vistaObtenida, equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(msjErrorEsperado));

    }

    private ModelAndView whenIntentoRecuperarContrasenia(String mail) {
        ModelAndView vistaModelada = controladorRecuperoContrasenia.recuperarContrasenia(mail);
        return vistaModelada;
    }

    private void givenQueNoHayRecuperoContrasenia() {
    }



}
