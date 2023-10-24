package com.uniquindio.edu.clinicaX.test;

import com.uniquindio.edu.clinicaX.dto.admin.MedicoDTOAdmin;
import com.uniquindio.edu.clinicaX.dto.paciente.DetalleCitaDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.DetallePacienteDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.RegistroCitaDTO;
import com.uniquindio.edu.clinicaX.dto.paciente.RegistroPacienteDTO;
import com.uniquindio.edu.clinicaX.enums.*;
import com.uniquindio.edu.clinicaX.servicios.implementacion.PacienteServicioImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class PacienteServicioTest {

    @Autowired
    private PacienteServicioImpl pacienteServicio;

    @Test
    public void crearPacienteTest() throws Exception {

        RegistroPacienteDTO pacienteDTO = new RegistroPacienteDTO(

                10,
                "79372864",
                "Orlando Tellez",
                "3153141256",
                Departamento.Antioquia,
                Ciudad.MEDELLIN,
                LocalDate.of(1990, 10, 7),
                Alergias.ASMAALERGICO,
                Eps.ALIANSALUD,
                TipoSangre.AB_NEGATIVO,
                "url_foto",
                "orlando@email.com",
                "Orlando1234"

        );
        int nuevo = pacienteServicio.regitrarPaciente(pacienteDTO);
        //Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void eliminarPaciente() throws Exception {
        pacienteServicio.eliminarCuentaPaciente(4);
        Assertions.assertThrows(Exception.class, () -> pacienteServicio.verDetallePaciente(2));
    }

//    @Test
//    @Sql("classpath:Dataset.sql")
//    public void actualizarPacienteTest() throws Exception{
//
//        DetallePacienteDTO guardado = pacienteServicio.verDetallePaciente(1);
//        DetallePacienteDTO modificado = new DetallePacienteDTO(
//
//                guardado.codigo(),
//                guardado.cedula(),
//                guardado.nombre(),
//                "3113245978",
//                guardado.correo(),
//                guardado.fechaNacimiento(),
//                guardado.urlFoto(),
//                guardado.ciudad(),
//                guardado.eps(),
//                guardado.tipoSangre(),
//                guardado.alergias()
//        );
//        pacienteServicio.actualizarCuentaPaciente(modificado);
//        DetallePacienteDTO objetoModificado = pacienteServicio.verDetallePaciente(1);
//        Assertions.assertEquals("3113245978", objetoModificado.telefono());
//    }

    @Test
    public void agendarCitaTest() throws Exception {
        RegistroCitaDTO registroCitaDTO = new RegistroCitaDTO(
                3,
                9,
                LocalDateTime.now(),
                LocalDateTime.of(2024,01,10,10,30),
                "motivo",
                EstadoCita.PROGRAMADA,
                Especializacion.PEDIATRIA
        );
        DetalleCitaDTO detalleCita = pacienteServicio.agendarCitas(registroCitaDTO);
        Assertions.assertNotEquals(0, detalleCita.codigoCita());
    }
}
