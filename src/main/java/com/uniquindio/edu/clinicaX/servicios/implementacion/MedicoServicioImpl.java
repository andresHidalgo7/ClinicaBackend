package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.medico.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.model.Atencion;
import com.uniquindio.edu.clinicaX.model.Cita;
import com.uniquindio.edu.clinicaX.model.Paciente;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;
    private final AtencionRepo atencionRepo;

    @Override
    public List<ListarCitasPendientesDTO> listarCitasPendiente(int codigoMedico) throws Exception {

        List<Cita> listaCitas = citaRepo.findAll(); // select * from pqrs
        List<ListarCitasPendientesDTO> respuesta = new ArrayList<>();

        for (Cita cita: listaCitas) {

            respuesta.add(new ListarCitasPendientesDTO(
               cita.getCodigo(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre(),
                    cita.getAtencion().getTratamiento(),
                    cita.getAtencion().getNotasMedicas(),
                    cita.getAtencion().getDiagnostico()
            ));
        }
        List<ListarCitasPendientesDTO> lista = listaCitas.stream().map(cita -> new ListarCitasPendientesDTO(

               cita.getCodigo(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre(),
                    cita.getAtencion().getTratamiento(),
                    cita.getAtencion().getNotasMedicas(),
                    cita.getAtencion().getDiagnostico()
               )).toList();

        return respuesta;
    }

    @Override
    public int atenderCita(RegistroAtencioDTO registroAtencioDTO) throws Exception {
        return 0;
    }

    @Override
    public List<ListarHistorialAtencionesPacienteDTO> listarHistorialAtencionesPaciente(int codigoPaciente) throws Exception {

        List<Atencion> listaAtencion = atencionRepo.findAll(); // select * from pqrs
        List<ListarHistorialAtencionesPacienteDTO> respuesta = new ArrayList<>();

        for (Atencion atencion: listaAtencion) {

            respuesta.add(new ListarHistorialAtencionesPacienteDTO(
                    atencion.getCodigo(),
                    atencion.getCita().getPaciente().getCedula(),
                    atencion.getCita().getPaciente().getNombre(),
                    atencion.getTratamiento(),
                    atencion.getNotasMedicas(),
                    atencion.getDiagnostico(),
                    atencion.getCita().getFechaCita()
            ));
        }
        List<ListarHistorialAtencionesPacienteDTO> lista = listaAtencion.stream().map(atencion -> new ListarHistorialAtencionesPacienteDTO(

                    atencion.getCodigo(),
                    atencion.getCita().getPaciente().getCedula(),
                    atencion.getCita().getPaciente().getNombre(),
                    atencion.getTratamiento(),
                    atencion.getNotasMedicas(),
                    atencion.getDiagnostico(),
                    atencion.getCita().getFechaCita()
               )).toList();

        return respuesta;
    }

    @Override
    public int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception {
        return 0;
    }

    @Override
    public List<ItemCitaDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {
        return null;
    }

    @Override
    public DetalleAtencionMedicoDTO verDetalleAtencion(int codigo) throws Exception {
        return null;
    }
}
