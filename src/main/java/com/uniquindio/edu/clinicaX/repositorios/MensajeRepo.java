package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepo extends JpaRepository <Mensaje, Integer> {

    List<Mensaje> findAllByPqrsCodigo(int codigoPqrs);

}
