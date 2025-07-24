package com.example.bankingapp.service;

import com.example.bankingapp.model.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listarClientes();

    Cliente obtenerCliente(Long id);

    Cliente crearCliente(Cliente cliente);

    Cliente actualizarCliente(Long id, Cliente clienteActualizado);

    void eliminarCliente(Long id);
}
