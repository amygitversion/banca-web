
package com.example.bankingapp.repository;

import com.example.bankingapp.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {}
