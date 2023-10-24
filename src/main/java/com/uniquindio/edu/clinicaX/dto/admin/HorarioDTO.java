package com.uniquindio.edu.clinicaX.dto.admin;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;

public record HorarioDTO(

        @NotBlank
        @Length(max = 2, message = "No puedes incluir mas de 2 caracteres para el dia")
        String dia,
        @NotBlank
        @Length (max = 4, message = "No puedes incluir mas de 4 caracteres para la hora de inicio")
        LocalTime horaInicio,
        @NotBlank
        @Length (max = 4, message = "No puedes incluir mas de 4 caracteres para la hora de salida")
        LocalTime horaSalida
) {
}
