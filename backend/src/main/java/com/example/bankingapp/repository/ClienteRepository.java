
package com.example.bankingapp.repository;

import com.example.bankingapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
