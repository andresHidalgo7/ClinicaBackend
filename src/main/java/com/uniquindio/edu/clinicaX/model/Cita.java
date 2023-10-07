package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 20)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    private LocalDateTime fechaCita;

    @Column(nullable = false, length = 50)
    private String motivo;

    @OneToMany(mappedBy = "cita")
    private List<Pqr> pqrs;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cita")
    private Atencion atencion;

    @Column(nullable = false, length = 15)
    private EstadoCita estadoCita;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
}
