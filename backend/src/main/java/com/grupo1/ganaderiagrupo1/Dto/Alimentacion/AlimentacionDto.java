package com.grupo1.ganaderiagrupo1.Dto.Alimentacion;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class AlimentacionDto  implements Serializable {
    private int alimentacion_id;
    private String nombre_suplemento;
    private int cantidad_suplemento;
    private String fecha_alimentacion;
    private String estado;
    private String codigo;
    private String nombre_ganado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
