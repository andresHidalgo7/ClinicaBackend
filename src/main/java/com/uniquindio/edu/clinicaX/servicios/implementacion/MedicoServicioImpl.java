package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.medico.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.model.Cita;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final PQRSRepo pqrsRepo;
    private final MedicoRepo medicoRepo;
    private final HorarioRepo horarioRepo;
    private final CitaRepo citaRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final PacienteRepo pacienteRepo;

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


        return null;
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
