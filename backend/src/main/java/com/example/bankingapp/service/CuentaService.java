package com.example.bankingapp.service;

import com.example.bankingapp.model.Cuenta;

import java.util.List;

public interface CuentaService {

    Cuenta obtenerCuenta(Long id);

    Cuenta crearCuenta(Cuenta cuenta);

    Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada);

    void eliminarCuenta(Long id);

    List<Cuenta> listarCuentas();
}