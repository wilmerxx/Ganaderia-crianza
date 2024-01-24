package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ControlEnfermedades {
        @Id
        private int control_id;
        private String tipo_control;
        private double pesoActual;
        private String fechaControl;
        private String observaciones;
        @ManyToOne(optional = false)
        @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
        private Ganado ganado;
        private String estado;
        private LocalDateTime creado;
        private LocalDateTime modificado;
}
