package com.uniquindio.edu.clinicaX.dto.paciente;

import com.uniquindio.edu.clinicaX.enums.Alergias;
import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Eps;
import com.uniquindio.edu.clinicaX.enums.TipoSangre;
import com.uniquindio.edu.clinicaX.model.Paciente;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record DetallePacienteDTO(
        int codigo,
        @NotBlank
        @Length(max = 10, message = "La cédula debe tener máximo 10 caracteres")
        String cedula,
        @NotBlank
        @Length(max = 200, message = "El nombre debe tener máximo 200 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
        String telefono,
        @NotBlank
        @Length(max = 80, message = "El correo debe tener máximo 80 caracteres")
        @Email(message = "Ingrese una dirección de correo electrónico válida")
        String correo,
        @NotNull
        @Past(message = "Seleccione una fecha de nacimiento correcta")
        LocalDate fechaNacimiento,
        @NotBlank
        String urlFoto,
        @NotNull
        Ciudad ciudad,
        @NotNull
        Eps eps,
        @NotNull
        TipoSangre tipoSangre,
        @NotBlank
        Alergias alergias

) {
    public DetallePacienteDTO(Paciente paciente) {
        this(
                paciente.getCodigo(),
                paciente.getCedula(),
                paciente.getNombre(),
                paciente.getTelefono(),
                paciente.getCorreo(),
                paciente.getFecha_nacimiento(),
                paciente.getUrlFoto(),
                paciente.getCiudad(),
                paciente.getEps(),
                paciente.getTipoSangre(),
                paciente.getAlergias()
        );
    }
}
