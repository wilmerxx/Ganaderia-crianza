package com.grupo1.ganaderiagrupo1.Dto.Alimentacion;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AlimentacionExisteDto {
    @NotNull (message = "El id del ganado no puede ser nulo")
    private int alimentacion_id;
    @NotNull (message = "El nombre del suplemento no puede ser nulo")
    private String nombre_suplemento;
    @NotNull (message = "La cantidad del suplemento no puede ser nulo")
    private int cantidad_suplemento;
    private String fecha_alimentacion;
    private String estado;
}
