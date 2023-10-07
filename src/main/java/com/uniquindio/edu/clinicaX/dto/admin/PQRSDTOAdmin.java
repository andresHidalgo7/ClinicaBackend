package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PQRSDTOAdmin(
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        @Length(max = 30, message = "No puedes incluir mas de 30 caracteres")
        String nombre,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDateTime fecha,
        @NotBlank
        EstadoPQRS estado


) {
}
