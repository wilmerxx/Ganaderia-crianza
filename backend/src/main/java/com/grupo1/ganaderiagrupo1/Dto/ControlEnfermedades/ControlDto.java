package com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ControlDto {
    private int controlId;
    private String nombreEnfermedad;
    private String tipoEnfermedad;
    private String fechaEnfermedad;
    private String fechaRecuperacion;
    private String estado;
    private String codigoGanado;
    private String nombreGanado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
