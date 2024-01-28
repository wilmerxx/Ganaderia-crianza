package com.grupo1.ganaderiagrupo1.Dto.Medicina;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicinaNuevoDto {
    @NotNull (message = "Los sintomas no pueden ser nulos")
    @Size(min = 3, max = 50, message = "Los sintomas deben tener entre 3 y 50 caracteres")
    private String sintomas;
    @NotNull (message = "El diagnostico del medicamento no puede ser nulo")
    @Size(min = 3, max = 50, message = "El diagnostico del medicamento debe tener entre 3 y 50 caracteres")
    private String diagnostico;
    @NotNull (message = "El tratamiento del medicamento no puede ser nulo")
    @Size(min = 3, max = 50, message = "El tratamiento del medicamento debe tener entre 3 y 50 caracteres")
    private String tratamiento;
    @NotNull (message = "La fecha de vacuna no puede ser nulo")
    @Size(min = 3, max = 50, message = "La fecha de vacuna debe tener entre 3 y 50 caracteres")
    private String fecha_vacuna;
    private String estado = "Activo";
    @NotNull (message = "El id del ganado no puede ser nulo")
    private int ganado_id;
}
