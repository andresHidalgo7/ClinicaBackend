package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import com.uniquindio.edu.clinicaX.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepo extends JpaRepository <Medico, Integer> {

    Medico findByCorreo(String correo);

    Medico findByCedula(String cedula);

    @Query("""
            select m from Medico m
            where m.estado = com.uniquindio.edu.clinicaX.enums.EstadoUsuario.ACTIVO and
            m.especializacion = :especialidad and
            m.codigo not in(
            select c.medico.codigo from Cita c
            where c.fechaCita= :fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionaMedicoConEspecialidadEnFecha(Especializacion especialidad, LocalDateTime fecha);


    Medico findActivoByCodigo(Integer idMedico);

    List<Medico> findByEspecializacionAndEstado(Especializacion especializacion, EstadoUsuario estadoUsuario);
}
