package com.uniquindio.edu.clinicaX.dto.admin;

import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Especializacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record MedicoDTOAdmin(

        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        int codigo,
        @NotBlank
        @Length(max = 30, message = "No puedes incluir mas de 30 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        String cedula,
        @NotBlank
        @Length(max = 10, message = "No puedes incluir mas de 10 caracteres")
        String telefono,
        @NotBlank
        Ciudad ciudad,
        @NotBlank
        Especializacion especializacion,
        @NotBlank
        @Email(message = "Ingrese una direccion de correo valida")
        @Length(max = 80, message = "El correo debe tener maximo 80 caracteres")
        String correo,
        @NotBlank
        List<HorarioDTO> horarios,
        @NotBlank
        String urlFoto

        ) {


}
