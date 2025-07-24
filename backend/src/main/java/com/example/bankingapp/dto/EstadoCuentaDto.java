package com.example.bankingapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class EstadoCuentaDto {
    private String cuentaNumero;
    private String tipoCuenta;
    private Double saldoDisponible;
    private List<MovimientoDto> movimientos;
}

