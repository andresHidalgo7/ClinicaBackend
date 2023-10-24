package com.uniquindio.edu.clinicaX.model;

import com.uniquindio.edu.clinicaX.dto.medico.DetalleAtencionMedicoDTO;
import com.uniquindio.edu.clinicaX.dto.medico.RegistroAtencioDTO;
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

    public Atencion(RegistroAtencioDTO registroAtencioDTO, Cita cita) {

        this.setCodigo(registroAtencioDTO.codigoCita());
        this.setDiagnostico(registroAtencioDTO.diagnostico());
        this.setTratamiento(registroAtencioDTO.tratamiento());
        this.setNotasMedicas(registroAtencioDTO.notasMedicas());
        this.setCita(cita);
    }

    public void actualizar(DetalleAtencionMedicoDTO detalleAtencionMedicoDTO, Cita cita) {

            this.setDiagnostico(detalleAtencionMedicoDTO.diagnostico());
            this.setTratamiento(detalleAtencionMedicoDTO.tratamiento());
            this.setNotasMedicas(detalleAtencionMedicoDTO.notasMedicas());
            this.setCita(cita);

    }
}
