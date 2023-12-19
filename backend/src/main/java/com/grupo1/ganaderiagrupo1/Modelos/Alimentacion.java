package com.grupo1.ganaderiagrupo1.Modelos;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_alimentacion;
    private String ganado_id;
}
