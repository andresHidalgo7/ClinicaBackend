package com.uniquindio.edu.clinicaX.dto.paciente;

import com.uniquindio.edu.clinicaX.enums.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegistroPacienteDTO(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        String cedula,
        @NotBlank
        @Length(max = 30, message = "No puedes incluir mas de 30 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 15, message = "No puedes incluir mas de 15 caracteres")
        String telefono,
        @NotBlank
        Departamento departamento,
        @NotBlank
        Ciudad ciudad,
        @NotBlank
        @Future(message = "Debes incluir una fecha valida")
        LocalDate fechaNacimiento,

        @NotBlank
        Alergias alergias,
        @NotBlank
        Eps eps,
        @NotBlank
        TipoSangre tipoSangre,
        @NotBlank
        String urlFoto
) {


}
