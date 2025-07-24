
package com.example.bankingapp.controller;

import com.example.bankingapp.dto.EstadoCuentaDto;
import com.example.bankingapp.model.Cliente;

import com.example.bankingapp.service.ClienteService;
import com.example.bankingapp.service.EstadoCuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final EstadoCuentaService estadoCuentaService;

    @GetMapping()
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerCliente(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente actualizado) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clienteId}/reportes")
    public ResponseEntity<List<EstadoCuentaDto>> estadoCuenta(
            @PathVariable Long clienteId,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        List<EstadoCuentaDto> reporte = estadoCuentaService.obtenerEstadoCuenta(clienteId, desde, hasta);
        return ResponseEntity.ok(reporte);
    }

    @GetMapping("/{clienteId}/reportes/pdf")
    public ResponseEntity<String> descargarEstadoCuentaBase64(
            @PathVariable Long clienteId,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        String base64 = estadoCuentaService.generarReporteBase64(clienteId, desde, hasta);
        return ResponseEntity.ok(base64);
    }


}
