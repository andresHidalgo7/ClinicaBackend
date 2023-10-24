package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.admin.*;
import com.uniquindio.edu.clinicaX.dto.paciente.DetallePQRSDTO;
import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;

import java.util.List;

public interface AdminServicio {


    int crearMedico(MedicoDTO medicoDTO)throws Exception;

    int actualizarMedico(MedicoDTOAdmin medicoDTOAdmin) throws Exception;

    void eliminarMedico(int codigo)throws Exception;

    MedicoDTOAdmin obtenerMedico(int codigo) throws Exception;

    InfoPQRSDTO verDetallePQRS(int codigo) throws Exception;

    List<CitaDTOAdmin> listarCitas() throws Exception;

    List<ItemMedicoDTO> listarMedico() throws Exception;

    List<PQRSDTOAdmin> listarPQRS() throws Exception;

    int responderPQRS (RegistroRespuestaDTO registroRespuestaDTO)throws Exception;

    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS)throws Exception;

}
