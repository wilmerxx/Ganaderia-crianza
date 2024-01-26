package com.grupo1.ganaderiagrupo1.Dto.Area;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AreaNuevoDto {
    @NotNull(message = "El nombre del area no puede ser nulo")
    private String nombreArea;
    @NotNull(message = "El tipo de area no puede ser nulo")
    private String tipoArea;
    @NotNull(message = "El tipo de pasto no puede ser nulo")
    private String tipoPasto;
    @NotNull(message = "La superficie no puede ser nula")
    private double superficie;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganadoId;
}
