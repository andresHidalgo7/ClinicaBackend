package com.uniquindio.edu.clinicaX.dto.paciente;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RespuestaDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codMensaje,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDateTime fecha,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String nombreUsuario,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String mensaje
) {
}
