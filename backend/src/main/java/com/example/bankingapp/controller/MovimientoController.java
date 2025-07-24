
package com.example.bankingapp.controller;

import com.example.bankingapp.model.Movimiento;
import com.example.bankingapp.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimiento(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.obtenerMovimiento(id));
    }


    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento creado = movimientoService.registrarMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento actualizado) {
        return ResponseEntity.ok(movimientoService.actualizarMovimiento(id, actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listarMovimientos() {
        return ResponseEntity.ok(movimientoService.listarMovimientos());
    }
}
