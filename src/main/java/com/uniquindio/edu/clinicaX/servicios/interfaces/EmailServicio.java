package com.uniquindio.edu.clinicaX.servicios.interfaces;

import com.uniquindio.edu.clinicaX.dto.EmailDTO;

public interface EmailServicio {

    void enviarCorreo(EmailDTO emailDTO) throws Exception;

}
