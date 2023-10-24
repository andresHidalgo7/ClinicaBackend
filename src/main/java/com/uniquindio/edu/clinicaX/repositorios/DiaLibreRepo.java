package com.uniquindio.edu.clinicaX.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaLibreRepo extends JpaRepository <com.uniquindio.edu.clinicaX.model.DiaLibre, Integer> {
}
