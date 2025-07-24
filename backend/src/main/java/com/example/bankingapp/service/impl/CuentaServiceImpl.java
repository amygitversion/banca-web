
package com.example.bankingapp.service.impl;

import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Cliente;
import com.example.bankingapp.model.Cuenta;
import com.example.bankingapp.repository.ClienteRepository;
import com.example.bankingapp.repository.CuentaRepository;
import com.example.bankingapp.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public Cuenta obtenerCuenta(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        Long clienteId = cuenta.getCliente().getId();

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (!"activo".equalsIgnoreCase(cliente.getEstado())) {
            throw new IllegalStateException("No se puede registrar una cuenta para un cliente inactivo");
        }

        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Long id, Cuenta actualizada) {
        Cuenta cuenta = obtenerCuenta(id);
        cuenta.setNumero(actualizada.getNumero());
        cuenta.setTipo(actualizada.getTipo());
        cuenta.setSaldoDisponible(actualizada.getSaldoDisponible());
        cuenta.setEstado(actualizada.getEstado());
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = obtenerCuenta(id);
        cuentaRepository.delete(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }
}