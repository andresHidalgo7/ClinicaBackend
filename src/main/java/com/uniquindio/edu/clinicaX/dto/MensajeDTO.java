package com.uniquindio.edu.clinicaX.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
){ }