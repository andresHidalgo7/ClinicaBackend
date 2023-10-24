package com.uniquindio.edu.clinicaX.servicios.validaciones;

import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamientoClinica implements ValidarCitas{
    @Override
    public void validar(RegistroCitaDTO registroCitaDTO) {
        var domingo = DayOfWeek.SUNDAY.equals(registroCitaDTO.fecha().getDayOfWeek());
        var antesDeApertura = registroCitaDTO.fecha().getHour()<7;
        var despuesDeCierre = registroCitaDTO.fecha().getHour()>19;
        if(domingo||antesDeApertura||despuesDeCierre){
            throw new ValidationException("El horario de la clinica es de " +
                    "Lunes a Sábado de 7:00AM a 19:00 horas");
        }
    }
}
