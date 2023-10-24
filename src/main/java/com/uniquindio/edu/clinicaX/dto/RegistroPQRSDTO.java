package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroPQRSDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoCita,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String motivo,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoPaciente,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String tipoPQRS,

        EstadoPQRS estadoPQRS
) {
}
