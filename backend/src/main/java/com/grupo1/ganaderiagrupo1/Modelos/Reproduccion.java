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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reproduccion_id;
    private String fecha_parto;
    private String estado_parto;
    private String observaciones;
    private int numero_crias;
    private String estado;
    @CreatedDate
    private LocalDateTime creado;
    @LastModifiedDate
    private LocalDateTime modificado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
    private Ganado ganado;
}
