package com.grupo1.ganaderiagrupo1.Dto.Medicina;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class MedicinaDto {
    private int medicina_id;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String fecha_vacuna;
    private String estado;
    private String codigoGanado;
    private String nombreGanado;
    private String estado_salud;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
