package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlNuevoDto {
    @NotNull(message = "El tipo de control no puede ser nulo")
    @Size(min = 3, max = 50, message = "El tipo de control debe tener entre 3 y 50 caracteres")
    private String tipo_control;
    @NotNull(message = "El peso actual no puede ser nulo")
    //validar que sea un numero positivo y sea mayor a 0
    @Min(value = 20, message = "El peso actual debe ser mayor a 20")
    private double pesoActual;
    @NotNull(message = "La fecha del control no puede ser nulo")
    private String fechaControl;
    @NotNull(message = "Las observaciones no puede ser nulo")
    @Size(min = 3, max = 300, message = "Las observaciones debe tener entre 3 y 300 caracteres")
    private String observaciones;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganadoId;
}
