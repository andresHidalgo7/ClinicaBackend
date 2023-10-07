package com.uniquindio.edu.clinicaX.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 100)
    private  String diagnostico;

    @Column(nullable = false, length = 80)
    private String tratamiento;

    @Column(nullable = false, length = 200)
    private String notasMedicas;

    @OneToOne(fetch = FetchType.LAZY)
    private Cita cita;
}
