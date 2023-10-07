package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepo extends JpaRepository <Medico, Integer> {

    Medico findByCorreo(String correo);

    Medico findByCedula(String cedula);
}
