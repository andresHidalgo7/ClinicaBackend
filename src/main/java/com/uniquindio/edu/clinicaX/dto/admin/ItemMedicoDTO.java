package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemMedicoDTO(
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        @Length (max = 10, message = "No puedes incluir mas de 10 caracteres")
        String cedula,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres")
        String nombre,
        @NotBlank
        String urlFoto,
        @NotBlank
        Especializacion especializacion) {
}
