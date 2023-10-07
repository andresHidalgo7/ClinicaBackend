package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.LoginDTO;
import com.uniquindio.edu.clinicaX.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    @Override
    public void login(LoginDTO loginDTO) throws Exception {

    }

    @Override
    public void recuperarContrasenia(LoginDTO loginDTO) throws Exception {

    }
}
