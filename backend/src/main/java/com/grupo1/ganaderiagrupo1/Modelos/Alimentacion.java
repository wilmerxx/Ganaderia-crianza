package com.grupo1.ganaderiagrupo1.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimentacion {
    private String alimentacion_id;
    private String nombre_suplemento;
    private String cantidad_suplemento;
    private Date fecha_alimentacion;
    private String ganado_id;
}
