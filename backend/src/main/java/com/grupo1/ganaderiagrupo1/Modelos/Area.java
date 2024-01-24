package com.grupo1.ganaderiagrupo1.Modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Area {
    @Id
    private int areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
    private String estado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
    private Ganado ganado;
    private LocalDateTime creado;
    private LocalDateTime modificado;

}

