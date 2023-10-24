package com.uniquindio.edu.clinicaX.dto;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token
){
}