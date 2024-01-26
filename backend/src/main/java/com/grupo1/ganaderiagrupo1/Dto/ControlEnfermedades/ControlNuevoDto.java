package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlNuevoDto {
    @NotNull(message = "El nombre de la enfermedad no puede ser nulo")
    private int controlId;
    @NotNull(message = "El nombre de la enfermedad no puede ser nulo")
    private String nombreEnfermedad;
    @NotNull(message = "El tipo de enfermedad no puede ser nulo")
    private String tipoEnfermedad;
    @NotNull(message = "La fecha de la enfermedad no puede ser nulo")
    private String fechaEnfermedad;
    @NotNull(message = "La fecha de recuperacion no puede ser nulo")
    private String fechaRecuperacion;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganadoId;
}
