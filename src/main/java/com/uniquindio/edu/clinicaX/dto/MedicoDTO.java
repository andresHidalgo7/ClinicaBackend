package com.uniquindio.edu.clinicaX.dto;

import com.uniquindio.edu.clinicaX.dto.admin.HorarioDTO;
import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record MedicoDTO(

        @NotBlank
        @Length(max = 30, message = "No puedes incluir mas de 30 caracteres para tu nombre")
        String nombre,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres para tu cedula")
        String cedula,
        @NotBlank
        @Length(max = 15, message = "No puedes incluir mas de 15 caracteres para tu telefono")
        String telefono,
        @NotBlank
        @Length(max = 30, message = "No puedes incluir mas de 30 caracteres para tu departamento")
        String departamento,
        @NotBlank
        Especializacion especializacion,
        @NotBlank
        Ciudad cudad,
        @NotBlank
        @Email(message = "Ingrese una direccion de correo valida")
        @Length(max = 80, message = "El correo debe tener maximo 80 caracteres")
        String correo,
        @NotBlank
        String password,
        @NotBlank
        List<HorarioDTO> horarios,
        @NotBlank
        String urlFoto,
        @NotBlank
        EstadoUsuario estadoUsuario

        ) {
}
