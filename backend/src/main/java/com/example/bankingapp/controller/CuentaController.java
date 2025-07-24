
package com.example.bankingapp.controller;

import com.example.bankingapp.model.Cuenta;
import com.example.bankingapp.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        return ResponseEntity.ok(cuentaService.listarCuentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuenta(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(id));
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuentaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

}
