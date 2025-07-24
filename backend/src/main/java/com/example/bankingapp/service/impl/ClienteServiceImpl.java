package com.example.bankingapp.service.impl;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Cliente;
import com.example.bankingapp.repository.ClienteRepository;
import com.example.bankingapp.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl  implements ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente actualizado) {
        Cliente cliente = obtenerCliente(id);
        cliente.setNombreUsuario(actualizado.getNombreUsuario());
        cliente.setContrasena(actualizado.getContrasena());
        cliente.setEstado(actualizado.getEstado());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerCliente(id);
        clienteRepository.delete(cliente);
    }
}
