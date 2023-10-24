package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import com.uniquindio.edu.clinicaX.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository <Cita, Integer> {

    List<Cita> findByPacienteCodigo(int codigoPaciente);

    List<Cita> findAllByAtencionCitaMedicoCodigo(int codigoMedico);

    List<Cita> findByEstadoCitaAndMedicoCodigo(EstadoCita estado, int codigoMedico);

    Boolean existsByMedicoCodigoAndFechaCita(
            Integer idMedico, LocalDateTime fecha);
    Boolean existsByPacienteCodigoAndFechaCitaBetween(
            Integer idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
    Integer countByPacienteCodigoAndEstadoCita(Integer idPaciente, EstadoCita estado);


}
