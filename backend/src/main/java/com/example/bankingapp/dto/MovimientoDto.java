package com.example.bankingapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovimientoDto {
    private String tipo;
    private LocalDate fecha;
    private Double monto;
    private Double saldoInicial;
    private Double saldoFinal;
    private String estado;
}
