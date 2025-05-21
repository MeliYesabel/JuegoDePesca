package com.tallerwebi.presentacion.autenticacion;

import com.tallerwebi.dominio.autenticacion.ServicioUsuarioI;
import com.tallerwebi.dominio.excepcion.UsuarioInexistenteException;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRecuperacionContraseniaTest {

    private ServicioUsuarioI servicioUsuarioI = mock(ServicioUsuarioI.class);
    private ControladorRecuperoContrasenia controladorRecuperoContrasenia = new ControladorRecuperoContrasenia(servicioUsuarioI);

    @Test
    public void queSePuedaCrearFormRecuperacionContrasenia() {
        givenQueNoExisteFormRecuperoContrasenia();
        ModelAndView mav = whenSeCreaElFormRecuperoContrasenia();
        thenSePuedeRecuperarContrasenia(mav);
    }

    @Test
    public void siNoHayEmailQueLaRecuperacionFalle(){
        givenNoHayRecupacionContrasenia();
        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("");
        thenLaRecuperacionFalla(vistaModelada, "email_vacio" ,"El email es obligatorio", "recuperar-contrasenia");
    }

    @Test
    public void siNoHayMailRegistradoQueLaRecuperacionFalle(){
        givenNoHayRecupacionContrasenia();
        //seteo mockito de servicio
         doThrow(UsuarioInexistenteException.class).when(servicioUsuarioI).buscarUsuarioPorEmail("belen@mail.com");

        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("belen@mail.com");
        thenLaRecuperacionFalla(vistaModelada,"no_existe","El email ingresado no se encuentra registrado.","recuperar-contrasenia");
    }

    @Test
    public void siHayMailRegistradoQueLaRecuperacionSeaExitosa(){
        givenNoHayRecupacionContrasenia();
        ModelAndView vistaModelada = whenIntentoRecuperarContrasenia("jesi@mail.com");
        thenLaRecuperacionEsExitosa(vistaModelada);

    }
    
    private void thenLaRecuperacionEsExitosa(ModelAndView mav) {
        String vistaEsperada = "login-pescador";
        String vistaObtenida = mav.getViewName();

        String mensajeDeErrorObtenido = mav.getModel().get("mensaje").toString();
        String mensajeDeErrorEsperado = "Se le envio un enlace de recuperacion a tu email.";

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));
        
    }

    private void thenLaRecuperacionFalla(ModelAndView vistaModelada, String keyEsperada, String msjErrorEsp, String vistaEsp) {
        String vistaEsperada = vistaEsp;
        String vistaObtenida = vistaModelada.getViewName();

        String mensajeDeErrorObtenido = vistaModelada.getModel().get(keyEsperada).toString();
        String mensajeDeErrorEsperado = msjErrorEsp;

        assertThat(vistaObtenida,equalTo(vistaEsperada));
        assertThat(mensajeDeErrorObtenido, equalTo(mensajeDeErrorEsperado));


    }

    private ModelAndView whenIntentoRecuperarContrasenia(String mail) {
       ModelAndView vistaModelada = controladorRecuperoContrasenia.recuperarContrasenia(mail);
       return vistaModelada;
    }

    private void givenNoHayRecupacionContrasenia() {
    }

    private void thenSePuedeRecuperarContrasenia(ModelAndView mav) {
        String vistaEsperada = "recuperar-contrasenia";
        String vistaGenerada = mav.getViewName();

        assertThat(vistaGenerada, equalTo(vistaEsperada));
        assertTrue(mav.getModel().containsKey("usuarioDto"));
    }

    private ModelAndView whenSeCreaElFormRecuperoContrasenia() {
        return controladorRecuperoContrasenia.mostrarFormRecuperoContrasenia();
    }

    private void givenQueNoExisteFormRecuperoContrasenia() {}
}
