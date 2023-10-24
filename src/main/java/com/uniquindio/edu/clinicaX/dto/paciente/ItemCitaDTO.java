package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import com.uniquindio.edu.clinicaX.model.Cita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ItemCitaDTO(

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
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para el nombre del medico")
        String nombreMedico,
        @NotBlank
        Especializacion especializacion,
        @NotBlank
        EstadoCita estadoCita,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDateTime fecha,
        @NotBlank
        @Length (max = 80, message = "No puedes incluir mas de 80 caracteres")
        String motivo
) {

        public ItemCitaDTO(Cita c){
                this(
                        c.getCodigo(),
                        c.getPaciente().getCedula(),
                        c.getPaciente().getNombre(),
                        c.getMedico().getNombre(),
                        c.getMedico().getEspecializacion(),
                        c.getEstadoCita(),
                        c.getFechaCita(),
                        c.getMotivo()
                );
        }
}


