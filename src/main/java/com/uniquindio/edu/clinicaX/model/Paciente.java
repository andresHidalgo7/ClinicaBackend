package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Usuario{

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 20)
    private LocalDate fecha_nacimiento;

    @Column(nullable = false)
    private Alergias alergias;

    @Column(nullable = false)
    private TipoSangre tipoSangre;

    @Column(nullable = false)
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;

}
