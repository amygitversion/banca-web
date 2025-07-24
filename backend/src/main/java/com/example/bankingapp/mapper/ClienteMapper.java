package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.ClienteDto;
import com.example.bankingapp.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);
    Cliente toEntity(ClienteDto dto);
}
