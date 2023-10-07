package com.uniquindio.edu.clinicaX.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record NuevaContraseniaTDO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        String codigo,
        @NotBlank
        String nuevaContrasenia
) {
}
