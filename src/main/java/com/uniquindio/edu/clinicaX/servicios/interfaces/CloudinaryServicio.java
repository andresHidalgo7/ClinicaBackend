package com.uniquindio.edu.clinicaX.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryServicio {

    Map subirImagen(MultipartFile file) throws Exception;
    Map eliminarImagen(String idImagen) throws Exception;
}
