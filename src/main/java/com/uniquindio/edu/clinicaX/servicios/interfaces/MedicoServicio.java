package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.medico.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;

import java.util.List;

public interface MedicoServicio {

    List<ListarCitasPendientesDTO> listarCitasPendiente(int codigoMedico) throws Exception;

    int atenderCita(RegistroAtencioDTO registroAtencioDTO)throws Exception;

    List<ListarHistorialAtencionesPacienteDTO> listarHistorialAtencionesPaciente(int codigoPaciente)throws Exception;

    int agendarDiaLibre(DiaLibreDTO diaLibreDTO)throws Exception;

    List <ItemCitaDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception;

    DetalleAtencionMedicoDTO verDetalleAtencion (int codigo)throws Exception;
}
