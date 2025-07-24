package com.example.bankingapp.dto;

public record ClienteDto(
        String nombreUsuario,
        String contrasena,
        String estado,

        PersonaDto personaDto
) {
}
