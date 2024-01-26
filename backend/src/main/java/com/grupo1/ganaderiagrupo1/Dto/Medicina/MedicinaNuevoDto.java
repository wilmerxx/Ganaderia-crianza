package com.grupo1.ganaderiagrupo1.Dto.Medicina;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicinaNuevoDto {
    @NotNull (message = "El id del ganado no puede ser nulo")
    private String sintomas;
    @NotNull (message = "El nombre del medicamento no puede ser nulo")
    private String diagnostico;
    @NotNull (message = "La dosis del medicamento no puede ser nulo")
    private String tratamiento;
    @NotNull (message = "La fecha de vacuna no puede ser nulo")
    private String fecha_vacuna;
    private String estado = "Activo";
    @NotNull (message = "El id del ganado no puede ser nulo")
    private int ganado_id;
}
