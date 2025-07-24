package com.example.bankingapp.service;

import com.example.bankingapp.model.Cuenta;
import com.example.bankingapp.model.Movimiento;
import com.example.bankingapp.repository.CuentaRepository;
import com.example.bankingapp.repository.MovimientoRepository;
import com.example.bankingapp.service.impl.MovimientoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class MovimientoServiceImplTest {

    @Autowired
    private MovimientoServiceImpl movimientoServiceImpl;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @BeforeEach
    public void setup() {
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();


        Cuenta cuenta = new Cuenta();
        cuenta.setNumero("0011223344");
        cuenta.setTipo("ahorros");
        cuenta.setSaldoDisponible(100.00);
        cuenta.setEstado("activa");
        cuentaRepository.save(cuenta);
    }

    @Test
    void registrarDebitoValido_actualizaSaldosCorrectamente() {
        Cuenta cuenta = cuentaRepository.findAll().get(0);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipo("debito");
        movimiento.setMonto(50.00);
        movimiento.setFecha(LocalDate.now());
        movimiento.setEstado("completado");
        movimiento.setCuenta(cuenta);

        Movimiento registrado = movimientoServiceImpl.registrarMovimiento(movimiento);

        Assertions.assertEquals(100.00, registrado.getSaldoInicial());
        Assertions.assertEquals(50.00, registrado.getSaldoFinal());
        Assertions.assertEquals(50.00, cuentaRepository.findById(cuenta.getId()).get().getSaldoDisponible());
    }

    @Test
    void registrarDebitoExcesivo_lanzaExcepcion() {
        Cuenta cuenta = cuentaRepository.findAll().get(0);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipo("debito");
        movimiento.setMonto(150.00);
        movimiento.setFecha(LocalDate.now());
        movimiento.setEstado("pendiente");
        movimiento.setCuenta(cuenta);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            movimientoServiceImpl.registrarMovimiento(movimiento);
        });
    }
}
