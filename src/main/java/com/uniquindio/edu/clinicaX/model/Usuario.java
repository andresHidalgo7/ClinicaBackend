package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.Ciudad;
import com.uniquindio.edu.clinicaX.enums.Departamento;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private Departamento departamento;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, unique = true, length = 10)
    private String telefono;

    @Lob
    @Column(nullable = false)
    private String urlFoto;

    @Column(nullable = false, unique = true, length = 20)
    private String estado;
}
