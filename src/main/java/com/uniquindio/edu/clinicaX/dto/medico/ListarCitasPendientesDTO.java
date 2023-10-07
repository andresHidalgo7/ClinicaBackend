package com.uniquindio.edu.clinicaX.dto.medico;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ListarCitasPendientesDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoCita,
        @NotBlank
        @Length (max = 10, message = "No puedes incluir mas de 10 caracteres para la cedula del paciente")
        String cedulaPaciente,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para el nombre del paciente")
        String nombrePaciente,
        @NotBlank
        @Length (max = 80, message = "No puedes incluir mas de 80 caracteres")
        String tratamiento,
        @NotBlank
        @Length (max = 200, message = "No puedes incluir mas de 200 caracteres")
        String notasMedicas,
        @NotBlank
        @Length (max = 80, message = "No puedes incluir mas de 80 caracteres")
        String diagnostico
) {
}
