package com.grupo1.ganaderiagrupo1.Modelos;

import jakarta.persistence.*;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
    private String estado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
    private Ganado ganado;
    @CreatedDate
    private LocalDateTime creado;
    @LastModifiedDate
    private LocalDateTime modificado;

}

