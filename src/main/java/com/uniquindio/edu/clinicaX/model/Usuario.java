package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Departamento;
import com.uniquindio.edu.clinicaX.enums.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta {

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, unique = true, length = 10)
    private String telefono;

    @Lob
    @Column(nullable = false, unique = true)
    private String urlFoto;

    @Column(nullable = false, length = 20)
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;
}
