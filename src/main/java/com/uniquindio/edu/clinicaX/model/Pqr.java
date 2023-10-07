package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.EstadoPQRS;
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
public class Pqr {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Lob
    @Column(nullable = false)
    private String motivo;

    @OneToMany(mappedBy = "pqr")
    private List<Mensaje> mensajes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cita cita;

    @Column(nullable = false)
    private EstadoPQRS estadoPQRS;

}
