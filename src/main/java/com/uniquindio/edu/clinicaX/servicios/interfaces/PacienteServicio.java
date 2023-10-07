package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.RegistroPQRSDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.*;

import java.util.List;

public interface PacienteServicio {

    int regitrarPaciente(RegistroPacienteDTO registroPacienteDTO)throws Exception;

    String eliminarCuentaPaciente(int codigo)throws Exception;

    int actualizarCuentaPaciente(int codigo, RegistroPacienteDTO registroPacienteDTO)throws Exception;

    String enviarLinkVerificacion(String email)throws Exception;

    String cambiarPassword(NuevaContraseniaTDO nuevaContraseniaTDO)throws Exception;

    int agendarCitas(RegistroCitaDTO registroCitaDTO) throws Exception;

    int crearPqrs(RegistroPQRSDTO registroPQRSDTO)throws Exception;

    List <ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente) throws Exception;

    DetallePQRSDTO verDetallePQRS(int codigo) throws Exception;

    int responderPQRS(RespuestaDTO respuestaDTO) throws Exception;

    List<ItemCitaDTO> listarCitasPaciente(int codigoPaciente) throws  Exception;

}
