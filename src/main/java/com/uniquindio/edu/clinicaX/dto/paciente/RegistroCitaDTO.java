package com.uniquindio.edu.clinicaX.dto.paciente;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroCitaDTO(

        @NotNull  Integer idPaciente,
        Integer idMedico,
        LocalDateTime fechaCreacion,
        @NotNull @Future
        LocalDateTime fecha,
        String motivo,
        EstadoCita estado,
        Especializacion especialidad
) {
}
