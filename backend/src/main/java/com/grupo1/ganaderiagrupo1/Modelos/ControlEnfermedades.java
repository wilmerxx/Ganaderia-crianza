package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ControlEnfermedades {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int control_id;
        private String tipo_control;
        private double pesoActual;
        private String fechaControl;
        private String observaciones;
        private String estado;
        @ManyToOne(optional = false)
        @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
        private Ganado ganado;
        @CreatedDate
        private LocalDateTime creado;
        @LastModifiedDate
        private LocalDateTime modificado;
}
