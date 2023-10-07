package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.LoginDTO;

public interface AutenticacionServicio {

    void login(LoginDTO loginDTO)throws Exception;

    void recuperarContrasenia(LoginDTO loginDTO)throws Exception;

}
