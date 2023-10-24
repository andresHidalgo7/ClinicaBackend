package com.uniquindio.edu.clinicaX.dto.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaLibreDTO(

        int codigo,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoMedico,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDate fecha
) {
}
