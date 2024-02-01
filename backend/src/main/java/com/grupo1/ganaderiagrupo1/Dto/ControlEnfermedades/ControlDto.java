package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlDto {
    private int control_id;
    private String tipo_control;
    private double pesoActual;
    private String fechaControl;
    private String observaciones;
    private String estado;
    private String codigoGanado;
    private String nombreGanado;
    private String estado_salud;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
