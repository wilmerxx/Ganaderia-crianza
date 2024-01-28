package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ControlExisteDto {
    @NotNull(message = "El nombre de la enfermedad no puede ser nulo")
    private int control_id;
    @NotNull(message = "El tipo de control no puede ser nulo")
    @Size(min = 3, max = 50, message = "El tipo de control debe tener entre 3 y 50 caracteres")
    private String tipo_control;
    @NotNull(message = "El peso actual no puede ser nulo")
    @Min(value = 20, message = "El peso actual debe ser mayor a 20")
    private double pesoActual;
    @NotNull(message = "La fecha del control no puede ser nulo")
    private String fechaControl;
    @NotNull(message = "Las observaciones no puede ser nulo")
    private String observaciones;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganadoId;
}
