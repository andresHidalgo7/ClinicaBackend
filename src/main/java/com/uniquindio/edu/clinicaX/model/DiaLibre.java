package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.dto.medico.DiaLibreDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiaLibre {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private LocalDate dia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    public DiaLibre(DiaLibreDTO diaLibreDTO, Medico medico){
        this.setDia(diaLibreDTO.fecha());
        this.setMedico(medico);
    }
}
