
package com.example.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    @Column(name = "saldo_final")
    private Double saldoFinal;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonBackReference
    private Cuenta cuenta;
}
