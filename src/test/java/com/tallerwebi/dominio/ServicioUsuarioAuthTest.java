package com.tallerwebi.dominio;

import com.tallerwebi.dominio.entidad.UsuarioAuth;
import com.tallerwebi.dominio.repositorio.RepositorioUsuarioAuth;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthI;
import com.tallerwebi.dominio.servicio.ServicioUsuarioAuthImpl;
import com.tallerwebi.presentacion.dto.UsuarioDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ServicioUsuarioAuthTest {
    private RepositorioUsuarioAuth repositorioUsuarioAuth;
    private ServicioUsuarioAuthImpl servicioUsuarioAuth;

    @BeforeEach
    public void init(){
        this.repositorioUsuarioAuth = mock(RepositorioUsuarioAuth.class);
        this.servicioUsuarioAuth =new ServicioUsuarioAuthImpl(this.repositorioUsuarioAuth);
    }

    @Test
    public void dadoUnUsuarioValidoQueElRegistroSeaExtoso(){
        UsuarioDto dto = new UsuarioDto("belen@mail.com", "Michi123!", "Michi123!");

        when(repositorioUsuarioAuth.buscarPorMail("belen@mail.com")).thenReturn(null);


        servicioUsuarioAuth.registrarUsuario(dto);

        ArgumentCaptor<UsuarioAuth> captor = ArgumentCaptor.forClass(UsuarioAuth.class);
        verify(repositorioUsuarioAuth).guardar(captor.capture());

        UsuarioAuth guardado = captor.getValue();
        assertEquals("belen@mail.com", guardado.getEmail());
        assertNotNull(guardado.getPassword());
    }

}
