package com.grupo1.ganaderiagrupo1.Dto.Reproduccion;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReproduccionNuevoDto {
    @NotNull(message = "La fecha de la reproduccion no puede ser nulo")
    private String fecha_parto;
    @NotNull(message = "El numero de crias no puede ser nulo")
    private String numero_crias;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganado_id;
}
