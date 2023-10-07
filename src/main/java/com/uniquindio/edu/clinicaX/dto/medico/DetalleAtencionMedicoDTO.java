package com.uniquindio.edu.clinicaX.dto.medico;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record DetalleAtencionMedicoDTO(

        @NotBlank
        @Length (max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigoCia,
        @NotBlank
        String nombrePaciente,
        @NotBlank
        String nombreMedico,
        @NotBlank
        Especializacion especializacion,
        @NotBlank
        @Future(message = "Seleccione una feca correcta")
        LocalDateTime fechaAtencion,
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
