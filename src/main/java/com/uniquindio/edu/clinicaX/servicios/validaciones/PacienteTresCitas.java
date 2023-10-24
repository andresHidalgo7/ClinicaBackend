package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import com.uniquindio.edu.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteTresCitas implements ValidarCitas{

    private final CitaRepo citaRepo;


    @Override
    public void validar(RegistroCitaDTO registroCitaDTO){
        //obtiene el num de citas programadas del paciente
        var numeroDeCitas = citaRepo.countByPacienteCodigoAndEstadoCita(registroCitaDTO.idPaciente(), EstadoCita.PROGRAMADA);
        if (numeroDeCitas>=3){
            throw new ValidationException("El paciente ya tiene 3 citas agendadas. ");
        }
    }
}
