package com.uniquindio.edu.clinicaX.test;

import com.uniquindio.edu.clinicaX.dto.ItemCitaDTO;
import com.uniquindio.edu.clinicaX.dto.medico.*;
import com.uniquindio.edu.clinicaX.servicios.implementacion.AdministradorServicioImpl;
import com.uniquindio.edu.clinicaX.servicios.implementacion.MedicoServicioImpl;
import com.uniquindio.edu.clinicaX.servicios.implementacion.PacienteServicioImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class MedicoServicioTest {

    @Autowired
    private MedicoServicioImpl medicoServicio;

    @Autowired
    private PacienteServicioImpl pacienteServicio;

    @Autowired
    private AdministradorServicioImpl administradorServicio;


    @Test
    @Sql("classpath:Dataset.sql")
    public void listarCitasPendientesTest() throws Exception {

        List<ListarCitasPendientesDTO> lista = medicoServicio.listarCitasPendiente(7);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void atenderCitaTest() throws Exception {


        RegistroAtencioDTO registroAtencioDTO =  new RegistroAtencioDTO(
                6,
                9,
                "notas",
                "tratamieo",
                "medicamentos"
        );

        int atencion = medicoServicio.atenderCita(registroAtencioDTO);
        Assertions.assertNotEquals(0, atencion);
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void listarHistorialAtencionesPacienteTest() throws Exception {

        List<ListarHistorialAtencionesPacienteDTO> lista = medicoServicio.listarHistorialAtencionesPaciente(3);
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void agendarDiaLibreTest() throws Exception{

        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(
                1,
                8,
                LocalDate.of(2023,12,30)

        );
        int dia = medicoServicio.agendarDiaLibre(diaLibreDTO);
        Assertions.assertNotEquals(0, dia);
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void citasRealizadasTest() throws Exception{

        List<ItemCitaDTO> lista = medicoServicio.listarCitasRealizadasMedico(6);
        lista.forEach(System.out::println);
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    @Sql("classpath:Dataset.sql")
    public void verDetalleAtencionTest() throws Exception{

        DetalleAtencionMedicoDTO guardado = medicoServicio.verDetalleAtencion(1);
        DetalleAtencionMedicoDTO modificado = new DetalleAtencionMedicoDTO(
                guardado.codigoCia(),
                guardado.nombrePaciente(),
                guardado.nombreMedico(),
                guardado.especializacion(),
                guardado.fechaAtencion(),
                "otro tratamiento",
                guardado.notasMedicas(),
                guardado.diagnostico()
        );
        medicoServicio.actualizar(modificado);
        DetalleAtencionMedicoDTO objetoModificado = medicoServicio.verDetalleAtencion(1);
        Assertions.assertEquals("otro tratamiento", objetoModificado.tratamiento());
    }

}
