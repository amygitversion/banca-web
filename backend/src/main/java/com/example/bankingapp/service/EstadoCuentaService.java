package com.example.bankingapp.service;

import com.example.bankingapp.dto.EstadoCuentaDto;

import java.time.LocalDate;
import java.util.List;

public interface EstadoCuentaService {
    public List<EstadoCuentaDto> obtenerEstadoCuenta(Long clienteId, LocalDate desde, LocalDate hasta);
    public String generarReporteBase64(Long clienteId, LocalDate desde, LocalDate hasta);
}
