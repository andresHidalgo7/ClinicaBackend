package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.repositorios.CitaRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoConConsulta implements ValidarCitas{

    private final CitaRepo citaRepo;

    @Override
    public void validar(RegistroCitaDTO registroCitaDTO) {
        if (registroCitaDTO.idMedico()==null)
            return;
        var medicoConConsulta = citaRepo.existsByMedicoCodigoAndFechaCita(
                registroCitaDTO.idMedico(), registroCitaDTO.fecha());
        if (medicoConConsulta){
            throw new ValidationException("Este médico ya tiene una consulta en ese horario");
        }
    }
}
