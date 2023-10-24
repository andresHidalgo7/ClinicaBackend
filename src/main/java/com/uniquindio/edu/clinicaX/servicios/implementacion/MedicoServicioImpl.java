package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.medico.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import com.uniquindio.edu.clinicaX.model.*;
import com.uniquindio.edu.clinicaX.model.DiaLibre;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.MedicoServicio;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;
    private final AtencionRepo atencionRepo;
    private final DiaLibreRepo diaLibreRepo;
    private final MedicoRepo medicoRepo;

    @Override
    public List<ListarCitasPendientesDTO> listarCitasPendiente(int codigoMedico) throws Exception {

        List<Cita> listaCitas = citaRepo.findByEstadoCitaAndMedicoCodigo(EstadoCita.PROGRAMADA, codigoMedico); // select * from pqrs
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

        Cita cita = validarCita(registroAtencioDTO.codigoCita());
        Atencion atencion = atencionRepo.save(new Atencion(registroAtencioDTO, cita));
        System.out.println("Atención " + atencion.getCodigo() + " creada");
        return atencion.getCodigo();
    }

    private Cita validarCita(int codigo)throws Exception {

        Optional<Cita> opcional = citaRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new Exception("No se encontró la cita");
        }
        return opcional.get();
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
        Medico medico = validarMedico(diaLibreDTO.codigoMedico());
        DiaLibre diaLibre = diaLibreRepo.save(new DiaLibre(diaLibreDTO,medico));
        System.out.println( "Dia Libre " + diaLibre.getCodigo() + " agendado");
        return diaLibre.getCodigo();
    }

    private Medico validarMedico(int codigo) {

        Optional<Medico> opcional = medicoRepo.findById(codigo);
        if (opcional.isEmpty()) {
            throw new ValidationException("No se encontró el médico con código "+codigo);
        }
        return opcional.get();
    }

    @Override
    public List<ItemCitaDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {
        List<Cita> citas = citaRepo.findAllByAtencionCitaMedicoCodigo(codigoMedico);
        if(citas.isEmpty()){
            throw new Exception("No existen citas atendidas por el medico "+codigoMedico);
        }
        return citas.stream()
                .map(ItemCitaDTO::new)
                .toList();
    }

    @Override
    public DetalleAtencionMedicoDTO verDetalleAtencion(int codigo) throws Exception {

        Optional<Atencion> optional = atencionRepo.findById(codigo);
        if( optional.isEmpty() ){
            throw new Exception("No existe una atención con el código "+codigo);
        }
        Atencion atencion = optional.get();
        return new DetalleAtencionMedicoDTO(atencion.getCita());    }

    public DetalleAtencionMedicoDTO actualizar(DetalleAtencionMedicoDTO detalleAtencionMedicoDTO) throws Exception {

        Atencion atencion = validarAtencion(detalleAtencionMedicoDTO.codigoCia());
        Cita cita = validarCita(detalleAtencionMedicoDTO.codigoCia());
        atencion.actualizar(detalleAtencionMedicoDTO, cita);
        return new DetalleAtencionMedicoDTO(atencion.getCita());
    }

    private Atencion validarAtencion (int codigo){
        System.out.println(codigo);
        Optional<Atencion> opcional = atencionRepo.findById(codigo);
        if( opcional.isEmpty() ){
            throw new ValidationException("No existe una atención con el código "+codigo);
        }
        return opcional.get();
    }
}
