package com.uniquindio.edu.clinicaX.dto.medico;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegistroAtencioDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoCita,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoMedico,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String notasMedicas,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String tratamiento,
        @NotBlank
        @Length(max = 50, message = "No puedes incluir mas de 50 caracteres")
        String diagnostico
) {
}
