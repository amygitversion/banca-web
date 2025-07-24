package com.example.bankingapp.dto;

public record PersonaDto(
        String nombre,
        String identificacion,
        String direccion,
        String telefono,
        String genero,
        String edad
) {}
