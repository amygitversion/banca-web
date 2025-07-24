package com.example.bankingapp.dto;

public record CuentaDto(
        Long id,
        String numero,
        String tipo,
        Double saldoDisponible,
        String estado,
        Long clienteId
) {}
