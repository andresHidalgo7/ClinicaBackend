package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CitaDTOAdmin(

        @NotBlank
        @Length(max = 10, message = "no puedes utilizar mas de 10 caracteres")
        int codigoCita,
        @NotBlank
        String cedulaPaciente,
        @NotBlank
        String nombrePaciente,
        @NotBlank
        String nombreMedico,
        @NotBlank
        Especializacion especialidad,
        @NotBlank
        EstadoCita estadoCita,
        @NotBlank
        @Future(message = "Ingresa una fecha de nacimiento correcta")
        LocalDateTime fecha

) {


}
