package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlEnfermedades {
        private String control_id;
        private String tipo_control;
        private double pesoActual;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date fechaControl;
        private String observaciones;
        private String ganado_id;
        private String estado;
}
