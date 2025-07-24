
package com.example.bankingapp.repository;

import com.example.bankingapp.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {}
