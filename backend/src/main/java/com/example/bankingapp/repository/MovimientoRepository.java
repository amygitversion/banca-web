
package com.example.bankingapp.repository;

import com.example.bankingapp.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {}
