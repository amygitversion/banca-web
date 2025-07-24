
package com.example.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero")
    private String numero;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "saldo_disponible")
    private Double saldoDisponible;
    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id" )
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Movimiento> movimientos;
}
