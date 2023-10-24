package com.uniquindio.edu.clinicaX.test;

import com.uniquindio.edu.clinicaX.dto.MedicoDTO;
import com.uniquindio.edu.clinicaX.dto.RegistroPQRSDTO;
import com.uniquindio.edu.clinicaX.dto.admin.*;
import com.uniquindio.edu.clinicaX.dto.medico.DiaLibreDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.DetalleCitaDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.DetallePQRSDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.RegistroPacienteDTO;
import com.uniquindio.edu.clinicaX.enums.*;
import com.uniquindio.edu.clinicaX.servicios.implementacion.AdministradorServicioImpl;
import com.uniquindio.edu.clinicaX.servicios.implementacion.PacienteServicioImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class AdministradorServicioTest {

    @Autowired
    private AdministradorServicioImpl administradorServicio;


    @Test
    public void crearMedicoTest() throws Exception {

        List<HorarioDTO> horarios = new ArrayList<>();
        horarios.add(new HorarioDTO("MARTES", LocalTime.of(7, 0, 0), LocalTime.of(14, 0, 0)));

        MedicoDTO medicoDTO = new MedicoDTO(
                "Andres",
                "1197347518",
                "3185863577",
                "Quindio",
                Especializacion.ANESTESIOLOGIA,
                Ciudad.ARMENIA,
                "andres@email.com",
                "123Andres",
                horarios,
                "url_foto",
                EstadoUsuario.ACTIVO
        );

        int nuevo = administradorServicio.crearMedico(medicoDTO);
        Assertions.assertNotEquals(0, nuevo);

    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void actualizarMedicoTest() throws Exception{

        MedicoDTOAdmin guardado = administradorServicio.obtenerMedico(6);
        MedicoDTOAdmin modificado = new MedicoDTOAdmin(

                guardado.codigo(),
                guardado.nombre(),
                guardado.cedula(),
                "3185867644",
                guardado.ciudad(),
                guardado.especializacion(),
                guardado.correo(),
                guardado.horarios(),
                guardado.urlFoto()
        );

        administradorServicio.actualizarMedico(modificado);
        MedicoDTOAdmin medicoModificado = administradorServicio.obtenerMedico(6);
        Assertions.assertEquals("3185867644", medicoModificado.telefono());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void eliminarMedico() throws Exception {
        administradorServicio.eliminarMedico(7);
        Assertions.assertThrows(Exception.class, () -> administradorServicio.obtenerMedico(7));
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void listarMedicos() throws Exception {

        List<ItemMedicoDTO> lista = administradorServicio.listarMedico();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void listarCitas() throws Exception {

        List<CitaDTOAdmin> lista = administradorServicio.listarCitas();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void listarPqrs() throws Exception {

        List<PQRSDTOAdmin> lista = administradorServicio.listarPQRS();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void responderPQRS() throws Exception {


        RegistroRespuestaDTO nuevoMensaje = new RegistroRespuestaDTO(
                1,
                1,
                5,
                "Como estas"
        );

//        //mensaje que se crea
        int nuevo = administradorServicio.responderPQRS(nuevoMensaje);
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void cambiarEstadoPQRS() throws Exception {
        administradorServicio.cambiarEstadoPQRS(
                1,
                EstadoPQRS.RESUELTO
        );
        InfoPQRSDTO objetoModificado = administradorServicio.verDetallePQRS(1);
        Assertions.assertEquals(EstadoPQRS.RESUELTO, objetoModificado.estado());
    }
}
