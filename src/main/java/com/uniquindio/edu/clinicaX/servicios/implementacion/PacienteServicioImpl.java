package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.dto.RegistroPQRSDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.*;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import com.uniquindio.edu.clinicaX.model.*;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.PacienteServicio;
import com.uniquindio.edu.clinicaX.servicios.validaciones.ValidarCitas;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PQRSRepo pqrsRepo;
    private final MedicoRepo medicoRepo;
    private final CitaRepo citaRepo;
    private final PacienteRepo pacienteRepo;
    private final CuentaRepo cuentaRepo;

    private final EmailServicioImpl emailServicio;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    List<ValidarCitas> validadores;

//    Listo Test
    @Override
    public int regitrarPaciente(RegistroPacienteDTO registroPacienteDTO) throws Exception {

        if (estaRepetidaCedula(registroPacienteDTO.cedula())){
            throw new Exception("La cedula: "+registroPacienteDTO.cedula()+" ya se encuentra en uso");
        }

        Paciente paciente = new Paciente();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        paciente.setCorreo(registroPacienteDTO.correo());
        paciente.setPasswd(passwordEncoder.encode(registroPacienteDTO.contrasenia()));

        paciente.setNombre(registroPacienteDTO.nombre());
        paciente.setCedula(registroPacienteDTO.cedula());
        paciente.setTelefono(registroPacienteDTO.telefono());
        paciente.setCiudad(registroPacienteDTO.ciudad());
        paciente.setFecha_nacimiento(registroPacienteDTO.fechaNacimiento());
        paciente.setAlergias(registroPacienteDTO.alergias());
        paciente.setEps(registroPacienteDTO.eps());
        paciente.setTipoSangre(registroPacienteDTO.tipoSangre());
        paciente.setCorreo(registroPacienteDTO.correo());
        paciente.setPasswd(registroPacienteDTO.contrasenia());
        paciente.setUrlFoto(registroPacienteDTO.urlFoto());

        pacienteRepo.save(paciente);
        return paciente.getCodigo();
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
        buscado.setEstado(EstadoUsuario.INACTIVO);
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
        paciente.setCiudad(registroPacienteDTO.ciudad());
        paciente.setFecha_nacimiento(registroPacienteDTO.fechaNacimiento());
        paciente.setAlergias(registroPacienteDTO.alergias());
        paciente.setEps(registroPacienteDTO.eps());
        paciente.setTipoSangre(registroPacienteDTO.tipoSangre());

        pacienteRepo.save(paciente);

        return paciente.getCodigo();
    }

    @Override
    public void enviarLinkVerificacion(String email) throws Exception {

        Optional<Cuenta> opcional = cuentaRepo.findByCorreo(email);
        if(opcional.isEmpty()){
            throw new ValidationException("No existe una cuenta con el correo "+email);
        }
        Cuenta cuenta = opcional.get();
        LocalDateTime fecha = LocalDateTime.now();
        String parametro = Base64.getEncoder().encodeToString( (cuenta.getCodigo()+";"+fecha).getBytes() );
        emailServicio.enviarCorreo(new EmailDTO(
                cuenta.getCorreo(),
                "Recuperación de contraseña",
                "Hola, para recuperar tu contraseña ingresa al siguiente link: https://api/recuperar-pasword/"+parametro
        ));

    }

    @Override
    public void cambiarPassword(NuevaContraseniaTDO nuevaContraseniaTDO) throws Exception {

        String parametro = new String(Base64.getDecoder().decode(nuevaContraseniaTDO.nuevaContrasenia()));
        String[] datos = parametro.split(";");
        int codigoCuenta = Integer.parseInt(datos[0]);
        LocalDateTime fecha = LocalDateTime.parse(datos[1]);
        if (fecha.plusMinutes(30).isBefore(LocalDateTime.now())){
            throw new Exception("Ell link de recuperación ha expirado");
        }
        Cuenta cuenta = obtenerCuentaCodigo(codigoCuenta);
        cuenta.setPasswd(passwordEncoder.encode(nuevaContraseniaTDO.nuevaContrasenia()));
    }

    private Cuenta obtenerCuentaCodigo(int codigoCuenta) {

        return null;
    }


    @Override
    public DetalleCitaDTO agendarCitas(RegistroCitaDTO registroCitaDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(registroCitaDTO.idPaciente());
        if (opcional.isEmpty()){
            throw new Exception("Este id para el paciente no fue encontrado");
        }
        if (registroCitaDTO.idMedico()!=null && !medicoRepo.existsById(registroCitaDTO.idMedico())){
            throw new Exception("Este id para el médico no fue encontrado");
        }
        //validaciones
        validadores.forEach(v->v.validar(registroCitaDTO));
        var paciente = opcional.get();
        var medico = seleccionarMedico(registroCitaDTO);

        if(medico==null){
            throw new Exception("No existen médicos disponibles para este " +
                    "horario y especialidad");
        }
        var cita = new Cita(LocalDateTime.now(), registroCitaDTO.fecha(), registroCitaDTO.motivo(), registroCitaDTO.estado(), medico, paciente);
        citaRepo.save(cita);
        return new DetalleCitaDTO(cita);
    }

    private Medico seleccionarMedico(RegistroCitaDTO registroCitaDTO) throws Exception{

        if (registroCitaDTO.idMedico()!=null){
            return medicoRepo.getReferenceById(registroCitaDTO.idMedico());
        }
        if (registroCitaDTO.especialidad()==null){
            throw new Exception("Debe seleccionar una especialidad para el médico");
        }
        return medicoRepo.seleccionaMedicoConEspecialidadEnFecha(registroCitaDTO.especialidad(),
                registroCitaDTO.fecha());
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

    public DetallePacienteDTO verDetallePaciente(int codigo) {

        Optional<Paciente> optional = pacienteRepo.findById(codigo);
        if( optional.isEmpty() ){
            throw new ValidationException("No existe un paciente con el código "+codigo);
        }
        Paciente paciente = optional.get();
        if(paciente.getEstado()==EstadoUsuario.INACTIVO){
            throw new ValidationException("El paciente esta Inactivo");
        }
        //Hacemos un mapeo de un objeto de tipo Paciente a un objeto de tipo DetallePacienteDTO
        return new DetallePacienteDTO(paciente);
    }

}
