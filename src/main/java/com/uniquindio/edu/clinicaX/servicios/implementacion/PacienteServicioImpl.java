package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.dto.RegistroPQRSDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.*;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import com.uniquindio.edu.clinicaX.model.Cita;
import com.uniquindio.edu.clinicaX.model.Paciente;
import com.uniquindio.edu.clinicaX.model.Pqr;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PQRSRepo pqrsRepo;
    private final MedicoRepo medicoRepo;
    private final HorarioRepo horarioRepo;
    private final CitaRepo citaRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final PacienteRepo pacienteRepo;

    @Override
    public int regitrarPaciente(RegistroPacienteDTO registroPacienteDTO) throws Exception {

        if (estaRepetidaCedula(registroPacienteDTO.cedula())){
            throw new Exception("La cedula: "+registroPacienteDTO.cedula()+" ya se encuentra en uso");
        }

        Paciente paciente = new Paciente();

        paciente.setNombre(registroPacienteDTO.nombre());
        paciente.setCedula(registroPacienteDTO.cedula());
        paciente.setTelefono(registroPacienteDTO.telefono());
        paciente.setDepartamento(registroPacienteDTO.departamento());
        paciente.setCiudad(registroPacienteDTO.ciudad());
        paciente.setFecha_nacimiento(registroPacienteDTO.fechaNacimiento());
        paciente.setAlergias(registroPacienteDTO.alergias());
        paciente.setEps(registroPacienteDTO.eps());
        paciente.setTipoSangre(registroPacienteDTO.tipoSangre());

        Paciente pacienteNuevo = pacienteRepo.save(paciente);


        return pacienteNuevo.getCodigo();
    }

    private boolean estaRepetidaCedula(String cedula) {

        return pacienteRepo.findByCedula(cedula) != null;
    }

    @Override
    public String eliminarCuentaPaciente(int codigo) throws Exception{

        Optional<Paciente> opcional = pacienteRepo.findById(codigo);

        if (opcional.isEmpty()){

            throw new Exception("No exise un paciente con el codigo: " + codigo);
        }
        Paciente buscado = opcional.get();
        buscado.setEstado(String.valueOf(EstadoUsuario.INACTIVO));
        pacienteRepo.save( buscado );

        return null;
    }

    @Override
    public int actualizarCuentaPaciente(int codigo, RegistroPacienteDTO registroPacienteDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(registroPacienteDTO.codigo());

        if (opcional.isEmpty()){
            throw new Exception("No exise un paciente con el codigo: " + registroPacienteDTO.codigo());
        }

        Paciente paciente = opcional.get();

        paciente.setNombre(registroPacienteDTO.nombre());
        paciente.setCedula(registroPacienteDTO.cedula());
        paciente.setTelefono(registroPacienteDTO.telefono());
        paciente.setDepartamento(registroPacienteDTO.departamento());
        paciente.setCiudad(registroPacienteDTO.ciudad());
        paciente.setFecha_nacimiento(registroPacienteDTO.fechaNacimiento());
        paciente.setAlergias(registroPacienteDTO.alergias());
        paciente.setEps(registroPacienteDTO.eps());
        paciente.setTipoSangre(registroPacienteDTO.tipoSangre());

        pacienteRepo.save(paciente);

        return paciente.getCodigo();
    }

    @Override
    public String enviarLinkVerificacion(String email) throws Exception {


        return null;
    }

    @Override
    public String cambiarPassword(NuevaContraseniaTDO nuevaContraseniaTDO) throws Exception {
        return null;
    }

    @Override
    public int agendarCitas(RegistroCitaDTO registroCitaDTO) throws Exception {
        return 0;
    }

    @Override
    public int crearPqrs(RegistroPQRSDTO registroPQRSDTO) throws Exception {
        Pqr pqr = new Pqr();

        pqr.setCodigo(registroPQRSDTO.codigoCita());
        pqr.setMotivo(registroPQRSDTO.motivo());
        pqr.setCodigo(registroPQRSDTO.codigoPaciente());
        pqr.setTipo(registroPQRSDTO.tipoPQRS());

        Pqr pqrNuevo = pqrsRepo.save(pqr);

        return pqrNuevo.getCodigo();
    }

    @Override
    public List<ItemPQRSDTO> listarPQRSPaciente(int codigoPaciente) throws Exception {

        List<Pqr> listaPqrs = pqrsRepo.findAll(); // select * from pqrs
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for (Pqr pqr: listaPqrs) {

            respuesta.add(new ItemPQRSDTO(
               pqr.getCodigo(),
               pqr.getEstadoPQRS(),
               pqr.getMotivo(),
               pqr.getFechaCreacion(),
               pqr.getCita().getPaciente().getNombre()
            ));
        }
        List<ItemPQRSDTO> lista = listaPqrs.stream().map(pqr -> new ItemPQRSDTO(

               pqr.getCodigo(),
               pqr.getEstadoPQRS(),
               pqr.getMotivo(),
               pqr.getFechaCreacion(),
               pqr.getCita().getPaciente().getNombre() )).toList();

        return respuesta;
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {
        return null;
    }

    @Override
    public int responderPQRS(RespuestaDTO respuestaDTO) throws Exception {
        return 0;
    }

    @Override
    public List <ItemCitaDTO> listarCitasPaciente(int codigoPaciente) throws Exception {

        List<Cita> listaCita = citaRepo.findAll(); // select * from pqrs
        List<ItemCitaDTO> respuesta = new ArrayList<>();

        for (Cita cita: listaCita) {

            respuesta.add(new ItemCitaDTO(
                    cita.getCodigo(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre(),
                    cita.getMedico().getNombre(),
                    cita.getMedico().getEspecializacion(),
                    cita.getEstadoCita(),
                    cita.getFechaCita(),
                    cita.getMotivo()
            ));
        }
        List<ItemCitaDTO> lista = listaCita.stream().map(cita -> new ItemCitaDTO(
                    cita.getCodigo(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre(),
                    cita.getMedico().getNombre(),
                    cita.getMedico().getEspecializacion(),
                    cita.getEstadoCita(),
                    cita.getFechaCita(),
                    cita.getMotivo() )).toList();
        return respuesta;
    }
}
