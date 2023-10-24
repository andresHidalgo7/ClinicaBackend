package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteSinConsulta implements ValidarCitas{

    private final CitaRepo citaRepo;

    @Override
    public void validar(RegistroCitaDTO registroCitaDTO) {

        var primerHorario = registroCitaDTO.fecha().withHour(7);
        var ultimoHorario = registroCitaDTO.fecha().withHour(19);

        var pacienteConConsulta = citaRepo.existsByPacienteCodigoAndFechaCitaBetween(
                registroCitaDTO.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para es día");
        }
    }
}
