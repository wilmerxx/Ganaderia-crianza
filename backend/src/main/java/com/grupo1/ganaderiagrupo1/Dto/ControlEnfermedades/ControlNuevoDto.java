package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlNuevoDto {
    @NotNull(message = "El tipo de control no puede ser nulo")
    private String tipo_control;
    @NotNull(message = "El peso actual no puede ser nulo")
    private double pesoActual;
    @NotNull(message = "La fecha del control no puede ser nulo")
    private String fechaControl;
    @NotNull(message = "Las observaciones no puede ser nulo")
    private String observaciones;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganadoId;
}
