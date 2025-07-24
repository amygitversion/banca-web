
package com.example.bankingapp.service.impl;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Cuenta;
import com.example.bankingapp.model.Movimiento;
import com.example.bankingapp.repository.CuentaRepository;
import com.example.bankingapp.repository.MovimientoRepository;
import com.example.bankingapp.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public Movimiento obtenerMovimiento(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
    }

    public Movimiento crearMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento actualizarMovimiento(Long id, Movimiento actualizado) {
        Movimiento movimiento = obtenerMovimiento(id);
        movimiento.setTipo(actualizado.getTipo());
        movimiento.setFecha(actualizado.getFecha());
        movimiento.setMonto(actualizado.getMonto());
        movimiento.setSaldoInicial(actualizado.getSaldoInicial());
        movimiento.setSaldoFinal(actualizado.getSaldoFinal());
        movimiento.setEstado(actualizado.getEstado());
        return movimientoRepository.save(movimiento);
    }

    public void eliminarMovimiento(Long id) {
        Movimiento movimiento = obtenerMovimiento(id);
        movimientoRepository.delete(movimiento);
    }

    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {

        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        Double saldoInicial = cuenta.getSaldoDisponible();
        movimiento.setSaldoInicial(saldoInicial);

        Double saldoFinal;
        switch (movimiento.getTipo().toLowerCase()) {
            case "credito":
                saldoFinal = saldoInicial + movimiento.getMonto();
                break;
            case "debito":
                if (movimiento.getMonto() > saldoInicial) {
                    throw new IllegalArgumentException("Monto del débito excede el saldo disponible");
                }
                saldoFinal = saldoInicial - movimiento.getMonto();
                break;
            default:
                throw new IllegalArgumentException("Tipo de movimiento no reconocido");
        }

        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldoFinal(saldoFinal);
        cuenta.setSaldoDisponible(saldoFinal);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }
}
