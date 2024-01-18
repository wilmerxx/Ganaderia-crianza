package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicina {
    @Id
    private String medicina_id;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String fecha_vacuna;
    private String estado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
    private Ganado ganado;
}
