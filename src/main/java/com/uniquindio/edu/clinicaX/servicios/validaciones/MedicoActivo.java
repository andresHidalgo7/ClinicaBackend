package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.repositorios.MedicoRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoActivo implements ValidarCitas{

    private final MedicoRepo medicoRepo;
    @Override
    public void validar(RegistroCitaDTO registroCitaDTO) {
        if (registroCitaDTO.idMedico()==null)
            return;
        var medicoactivo = medicoRepo.findById(registroCitaDTO.idMedico());
        if (medicoactivo == null){
            throw new ValidationException("No se permite agendar citas con médicos " +
                    "inactivos en el sistema");
        }
    }
}
