package com.tallerwebi.dominio.entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends UsuarioAuth{
    //por el momento no tiene atributos solo su id
}
