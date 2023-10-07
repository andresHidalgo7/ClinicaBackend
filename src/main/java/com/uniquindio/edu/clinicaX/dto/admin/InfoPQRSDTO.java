package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.dto.paciente.RespuestaDTO;
import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record InfoPQRSDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        EstadoPQRS estado,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres")
        String motivoPQRS,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para el nombre del paciente")
        String nombrePaciente,
        @NotBlank
        @Length (max = 30, message = "No puedes incluir mas de 30 caracteres para el nombre del medico")
        String nombreMedico,
        @NotBlank
        Especializacion especialidad,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDateTime fecha,
        @NotBlank
        List<RespuestaDTO> mensajes

) {
}
