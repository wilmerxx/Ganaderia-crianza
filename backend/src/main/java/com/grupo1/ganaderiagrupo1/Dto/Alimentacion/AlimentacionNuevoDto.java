package com.grupo1.ganaderiagrupo1.Dto.Alimentacion;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AlimentacionNuevoDto {
    private int alimentacion_id;
    private String nombre_suplemento;
    private int cantidad_suplemento;
    private String fecha_alimentacion;
    private String estado = "Activo";
    private int ganado_id;
}
