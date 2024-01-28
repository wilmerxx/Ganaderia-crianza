package com.grupo1.ganaderiagrupo1.Dto.Reproduccion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReproduccionNuevoDto {
    @NotNull(message = "La fecha de la reproduccion no puede ser nulo")
    private String fecha_parto;
    @NotNull(message = "El estado de la reproduccion no puede ser nulo")
    @Size(min = 1, max = 1, message = "El estado de la reproduccion debe tener 1 caracter")
    private String estado_parto;
    @NotNull(message = "Las observaciones no puede ser nulo")
    @Size(min = 1, max = 300, message = "Las observaciones debe tener entre 1 y 300 caracteres")
    private String observaciones;
    @NotNull(message = "El numero de crias no puede ser nulo")
    private int numero_crias;
    private String estado = "Activo";
    @NotNull(message = "El id del ganado no puede ser nulo")
    private int ganado_id;
}
