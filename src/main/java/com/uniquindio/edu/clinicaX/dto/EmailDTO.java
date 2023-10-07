package com.uniquindio.edu.clinicaX.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(

        @NotBlank
        @Length(max = 80, message = "No puedes incluir mas de 80 caracteres en el asunto del correo")
        String asunto,
        @NotBlank
        @Length (max = 200, message = "No puedes incluir mas de 200 caracteres en el cuerpo del correo")
        String cuerpo,
        @NotBlank
        @Email (message = "Ingrese una direccion de correo valida")
        @Length (max = 80, message = "El correo debe tener maximo 80 caracteres")
        String destino
) {


}
