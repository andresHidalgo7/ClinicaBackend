package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository <Horario, Integer> {

    List<Horario> findAllByMedicoCodigo(int codigo);

}
