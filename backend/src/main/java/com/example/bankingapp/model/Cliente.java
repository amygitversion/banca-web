
package com.example.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Persona {

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @Column(name = "estado", nullable = false)
    private String estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cuenta> cuentas;
}
