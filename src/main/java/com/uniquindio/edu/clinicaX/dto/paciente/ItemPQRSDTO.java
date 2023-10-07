package com.uniquindio.edu.clinicaX.dto.paciente;

import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemPQRSDTO(
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        EstadoPQRS estadoPQRS,
        @NotBlank
        @Length (max = 50, message = "No puedes incluir mas de 50 caracteres")
        String motivo,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDateTime fecha,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para el nombre del paciente")
        String nombrePaciente

) {
}
