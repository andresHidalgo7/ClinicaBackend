package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.*;
import com.uniquindio.edu.clinicaX.dto.admin.*;
import com.uniquindio.edu.clinicaX.dto.paciente.RespuestaDTO;
import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import com.uniquindio.edu.clinicaX.model.*;
import com.uniquindio.edu.clinicaX.repositorios.*;
import com.uniquindio.edu.clinicaX.servicios.interfaces.AdminServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdminServicio {

    private final PQRSRepo pqrsRepo;
    private final MedicoRepo medicoRepo;
    private final HorarioRepo horarioRepo;
    private final CitaRepo citaRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;


    @Override
    public int crearMedico(MedicoDTO medicoDTO) throws Exception {

        if (estaRepetidaCedula(medicoDTO.cedula())){
            throw new Exception("La cedula: "+medicoDTO.cedula()+" ya se encuentra en uso");
        }
        if (estaRepetidaCorreo(medicoDTO.correo())){
            throw new Exception("El correo: "+medicoDTO.correo()+" ya se encuentra en uso");
        }
        Medico medico = new Medico();

        medico.setNombre(medicoDTO.nombre());
        medico.setCedula(medicoDTO.cedula());
        medico.setTelefono(medicoDTO.telefono());
        medico.setCiudad(medicoDTO.cudad());
        medico.setCorreo(medicoDTO.correo());
        medico.setPasswd(medicoDTO.password());
        medico.setUrlFoto(medicoDTO.urlFoto());
        medico.setEstado(String.valueOf(EstadoUsuario.ACTIVO));

        Medico medicoNuevo = medicoRepo.save(medico);
        asignarHorariosMedico( medicoNuevo, medicoDTO.horarios());

        return medicoNuevo.getCodigo();
    }

    private void asignarHorariosMedico(Medico medicoNuevo, List<HorarioDTO> horarios) {

        for( HorarioDTO horarioDTO : horarios ){

            Horario horario = new Horario();
            horario.setDia( horarioDTO.dia() );
            horario.setHoraInicio( horarioDTO.horaInicio() );
            horario.setHoraFin( horarioDTO.horaSalida() );
            horario.setMedico( medicoNuevo );

            horarioRepo.save(horario);
        }
    }

    private boolean estaRepetidaCorreo(String correo) {

       return medicoRepo.findByCorreo(correo) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {

        return medicoRepo.findByCedula(cedula) != null;
    }

    @Override
    public int actualizarMedico(int codigo, MedicoDTOAdmin medicoDTOAdmin) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(medicoDTOAdmin.codigo());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el codigo "+medicoDTOAdmin.codigo());
        }

        Medico buscado = opcional.get();

        buscado.setCedula(medicoDTOAdmin.cedula() );
        buscado.setTelefono(medicoDTOAdmin.telefono());
        buscado.setNombre(medicoDTOAdmin.nombre() );
        buscado.setEspecializacion(Especializacion.CARDIOLOGIA);
        buscado.setCiudad(Ciudad.ARMENIA);
        buscado.setCorreo(medicoDTOAdmin.correo() );
        buscado.setUrlFoto(medicoDTOAdmin.urlFoto());

        medicoRepo.save( buscado );

        return buscado.getCodigo();
    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();
        buscado.setEstado(String.valueOf(EstadoUsuario.INACTIVO));
        medicoRepo.save( buscado );

        //medicoRepo.delete(buscado);
    }
    @Override
    public String responderPQRS(int codigo) throws Exception {
        return null;
    }

    @Override
    public MedicoDTOAdmin obtenerMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        List<Horario> horarios = horarioRepo.findAllByMedicoCodigo(codigo);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( Horario horario : horarios ){
            horariosDTO.add( new HorarioDTO(
                    horario.getDia(),
                    horario.getHoraInicio(),
                    horario.getHoraFin()
            ) );
        }
        return new MedicoDTOAdmin(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getTelefono(),
                buscado.getCiudad(),
                buscado.getEspecializacion(),
                buscado.getCorreo(),
                horariosDTO,
                buscado.getUrlFoto()
        );
    }

    @Override
    public InfoPQRSDTO verDetallePQRS(int codigo) throws Exception {
        Optional<Pqr> opcional = pqrsRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+codigo);
        }

        Pqr buscado = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);

        return new InfoPQRSDTO(
                buscado.getCodigo(),
                buscado.getEstadoPQRS(),
                buscado.getMotivo(),
                buscado.getCita().getPaciente().getNombre(),
                buscado.getCita().getMedico().getNombre(),
                buscado.getCita().getMedico().getEspecializacion(),
                buscado.getFechaCreacion(),
                convertirRespuestasDTO(mensajes)
        );
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(msj -> new RespuestaDTO(
                msj.getCodigo(),
                msj.getFechaCreacion(),
                msj.getCuenta().getCorreo(),
                msj.getContenido()

        )).toList();
    }
    @Override
    public List<CitaDTOAdmin> listarCitas() throws Exception {

        List<Cita> citas = citaRepo.findAll();
        List<CitaDTOAdmin> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
            respuesta.add( new CitaDTOAdmin(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecializacion(),
                    c.getEstadoCita(),
                    c.getFechaCita()
            ) );
        }

        return respuesta;
    }
    @Override
    public List<ItemMedicoDTO> listarMedico() throws Exception {
        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay médicos registrados");
        }

        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico medico: medicos){
            respuesta.add( new ItemMedicoDTO(
                    medico.getCodigo(),
                    medico.getCedula(),
                    medico.getNombre(),
                    medico.getUrlFoto(),
                    medico.getEspecializacion()) );
        }

        /*List<ItemMedicoDTO> respuesta = medicos.stream().map( m -> new ItemMedicoDTO(
                m.getCodigo(),
                m.getCedula(),
                m.getNombre(),
                m.getUrlFoto(),
                m.getEspecialidad()
        ) ).toList();*/

        return respuesta;
    }

    @Override
    public List<PQRSDTOAdmin> listarPQRS(String codigo) throws Exception {

        List<Pqr> listaPqrs = pqrsRepo.findAll(); // select * from pqrs
        List<PQRSDTOAdmin> respuesta = new ArrayList<>();

        for (Pqr p: listaPqrs) {

        respuesta.add(new PQRSDTOAdmin(

            p.getCodigo(),
            p.getCita().getMedico().getNombre(),
            p.getFechaCreacion(),
            p.getEstadoPQRS()) );
        }

       List<PQRSDTOAdmin>lista = listaPqrs.stream().map(p -> new PQRSDTOAdmin(

                p.getCodigo(),
                p.getCita().getMedico().getNombre(),
                p.getFechaCreacion(),
                p.getEstadoPQRS() )).toList();
        return respuesta;
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        Optional<Pqr> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if(opcionalPQRS.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+registroRespuestaDTO.codigoPQRS());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setPqr(opcionalPQRS.get());
        mensajeNuevo.setFechaCreacion(LocalDateTime.now() );
        mensajeNuevo.setCuenta(opcionalCuenta.get());
        mensajeNuevo.setContenido(registroRespuestaDTO.mensaje() );

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<Pqr> opcional = pqrsRepo.findById(codigoPQRS);

        if (opcional.isEmpty()){
            throw new Exception("El codigo "+codigoPQRS+" no se asocia a ningun PQRS");
        }

        Pqr pqr = opcional.get();
        pqr.setEstadoPQRS(estadoPQRS);

        pqrsRepo.save(pqr);
    }
}
