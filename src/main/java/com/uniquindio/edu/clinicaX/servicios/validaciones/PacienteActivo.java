package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.repositorios.PacienteRepo;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteActivo implements ValidarCitas{

    private final PacienteRepo pacienteRepo;

    @Override
    public void validar(RegistroCitaDTO registroCitaDTO) {

        if(registroCitaDTO.idPaciente()==null){
            return;
        }
        var pacienteActivo = pacienteRepo.findActivoByCodigo(registroCitaDTO.idPaciente());
        if (pacienteActivo==null){
            throw new ValidationException("No se permite agendar citas con pacientes inactivos " +
                    "en el sistema");
        }
    }
}
