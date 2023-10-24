package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepo extends JpaRepository <Paciente, Integer> {

    Paciente findByCedula(String cedula);
    Paciente findActivoByCodigo(Integer idPaciente);


}
