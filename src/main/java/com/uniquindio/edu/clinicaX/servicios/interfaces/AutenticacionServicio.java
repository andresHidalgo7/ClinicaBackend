package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.LoginDTO;
import com.uniquindio.edu.clinicaX.dto.TokenDTO;

public interface AutenticacionServicio {

    TokenDTO login(LoginDTO loginDTO)throws Exception;

    void recuperarContrasenia(LoginDTO loginDTO)throws Exception;

}
