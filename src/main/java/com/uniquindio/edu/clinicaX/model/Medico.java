package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.enums.Especializacion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends  Usuario{

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false)
    private Especializacion especializacion;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diaLibres;

    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}
