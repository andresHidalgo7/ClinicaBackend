package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepo extends JpaRepository <Cita, Integer> {
}
