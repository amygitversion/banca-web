package com.example.bankingapp.service;

import com.example.bankingapp.model.Movimiento;

import java.util.List;

public interface MovimientoService {

    Movimiento obtenerMovimiento(Long id);

    Movimiento crearMovimiento(Movimiento movimiento);

    Movimiento actualizarMovimiento(Long id, Movimiento actualizado);

    void eliminarMovimiento(Long id);

    List<Movimiento> listarMovimientos();

    Movimiento registrarMovimiento(Movimiento movimiento);
}
