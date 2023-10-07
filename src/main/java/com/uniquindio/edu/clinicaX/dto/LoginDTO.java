package com.uniquindio.edu.clinicaX.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(

        @NotBlank
        @Email(message = "Ingrese una direccion de correo valida")
        @Length(max = 80, message = "El correo debe tener maximo 80 caracteres")
        String email,
        @NotBlank
        String conrasenia
) {
}
