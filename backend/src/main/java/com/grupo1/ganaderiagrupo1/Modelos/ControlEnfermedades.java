package com.grupo1.ganaderiagrupo1.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlEnfermedades {
        private String control_id;
        private String tipo_control;
        private String pesoActual;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private String fechaControl;
        private String observaciones;
        private String ganado_id;
        private String estado;
}
