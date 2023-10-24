package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAnticipacion implements ValidarCitas{
    @Override
    public void validar(RegistroCitaDTO registroCitaDTO){

        var ahora  = LocalDateTime.now();
        var horaDeConsulta = registroCitaDTO.fecha();
        var diferenciaDe30Min = Duration.between(ahora, horaDeConsulta).toMinutes()<30;
        if (diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con al menos " +
                    "30 minitos de anticipacion");
        }
    }
}
