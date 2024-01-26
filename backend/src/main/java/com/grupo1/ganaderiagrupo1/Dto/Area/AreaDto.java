package com.grupo1.ganaderiagrupo1.Dto.Area;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AreaDto {
    private int areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
    private String estado;
    private String codigoGanado;
    private String nombreGanado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
