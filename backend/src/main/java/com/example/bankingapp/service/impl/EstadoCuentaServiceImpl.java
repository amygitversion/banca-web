package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.EstadoCuentaDto;
import com.example.bankingapp.dto.MovimientoDto;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.model.Cliente;
import com.example.bankingapp.model.Cuenta;
import com.example.bankingapp.repository.ClienteRepository;
import com.example.bankingapp.service.EstadoCuentaService;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

    private final ClienteRepository clienteRepository;

    public List<EstadoCuentaDto> obtenerEstadoCuenta(Long clienteId, LocalDate desde, LocalDate hasta) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        List<EstadoCuentaDto> estado = new ArrayList<>();

        for (Cuenta cuenta : cliente.getCuentas()) {
            EstadoCuentaDto dto = new EstadoCuentaDto();
            dto.setCuentaNumero(cuenta.getNumero());
            dto.setTipoCuenta(cuenta.getTipo());
            dto.setSaldoDisponible(cuenta.getSaldoDisponible());

            List<MovimientoDto> movimientos = cuenta.getMovimientos().stream()
                    .filter(m -> !m.getFecha().isBefore(desde) && !m.getFecha().isAfter(hasta))
                    .map(m -> {
                        MovimientoDto mdto = new MovimientoDto();
                        mdto.setTipo(m.getTipo());
                        mdto.setFecha(m.getFecha());
                        mdto.setMonto(m.getMonto());
                        mdto.setSaldoInicial(m.getSaldoInicial());
                        mdto.setSaldoFinal(m.getSaldoFinal());
                        mdto.setEstado(m.getEstado());
                        return mdto;
                    }).toList();

            dto.setMovimientos(movimientos);
            estado.add(dto);
        }
        return estado;
    }

    public String generarReporteBase64(Long clienteId, LocalDate desde, LocalDate hasta) {
        List<EstadoCuentaDto> estado = obtenerEstadoCuenta(clienteId, desde, hasta);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Estado de Cuenta"));
            document.add(new Paragraph("Cliente ID: " + clienteId));
            document.add(new Paragraph("Rango de fechas: " + desde + " a " + hasta));
            document.add(new Paragraph(" "));

            for (EstadoCuentaDto cuenta : estado) {
                document.add(new Paragraph("Cuenta: " + cuenta.getCuentaNumero()));
                document.add(new Paragraph("Tipo: " + cuenta.getTipoCuenta()));
                document.add(new Paragraph("Saldo Disponible: $" + cuenta.getSaldoDisponible()));
                document.add(new Paragraph("Movimientos:"));

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                table.addCell("Fecha");
                table.addCell("Tipo");
                table.addCell("Monto");
                table.addCell("Saldo Inicial");
                table.addCell("Saldo Final");

                for (MovimientoDto m : cuenta.getMovimientos()) {
                    table.addCell(m.getFecha().toString());
                    table.addCell(m.getTipo());
                    table.addCell(m.getMonto().toString());
                    table.addCell(m.getSaldoInicial().toString());
                    table.addCell(m.getSaldoFinal().toString());
                }

                document.add(table);
                document.add(new Paragraph(" "));
            }

            document.close();
            byte[] pdfBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

}

