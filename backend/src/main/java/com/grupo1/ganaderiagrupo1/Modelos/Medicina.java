package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicina {
    private String medicina_id;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_vacuna;
    private String ganado_id;
}
