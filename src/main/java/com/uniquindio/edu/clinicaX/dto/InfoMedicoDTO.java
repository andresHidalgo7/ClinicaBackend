package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Especializacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record InfoMedicoDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        @Length (max = 10, message = "No puedes incluir mas de 10 caracteres")
        String cedula,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para tu nombre")
        String nombre,
        @NotBlank
        Ciudad ciudad,
        @NotBlank
        Especializacion especializacion,
        @NotBlank
        @Length (max = 15, message = "No puedes incluir mas de 15 caracteres para tu telefono")
        String telefono,
        @NotBlank
        @Email(message = "Ingrese una direccion de correo valida")
        @Length (max = 80, message = "El correo debe tener maximo 80 caracteres")
        String correo,
        @NotBlank
        String urlFoto

) {
}
