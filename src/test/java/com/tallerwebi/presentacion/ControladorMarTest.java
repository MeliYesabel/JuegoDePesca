package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ControladorMarTest {

    @Test
    public void paraJugarqueSeCreeLaEscenaDeJuego() {
        //given()
        dadoQueNoHayEscenaDeJuego();
        //when()
        ModelAndView mvEscenaDeJuegoGenerada = whenGeneroEscenaDeJuego();

        //then()
        thenSeMuestraLaEscenaDeJuegoDeModoExitosa(mvEscenaDeJuegoGenerada);
    }

    private void thenSeMuestraLaEscenaDeJuegoDeModoExitosa(ModelAndView mvEscenaDeJuegoGenerada) {
        String vistaEsperada = "mar-escena-de-juego";
        String vistaObtenida = mvEscenaDeJuegoGenerada.getViewName();
        assertThat(vistaEsperada, equalTo(vistaObtenida));
    }

    private ModelAndView whenGeneroEscenaDeJuego() {
        ControladorMar controladorMar = new ControladorMar();
        ModelAndView vistaEscenaDeJuego = controladorMar.mostrarEscenaDeJuego();
        return vistaEscenaDeJuego;
    }

    private void dadoQueNoHayEscenaDeJuego() {
    }

}
