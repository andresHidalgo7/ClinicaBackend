package com.uniquindio.edu.clinicaX.repositorios;

import com.uniquindio.edu.clinicaX.model.Pqr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PQRSRepo extends JpaRepository <Pqr, Integer>{


}
