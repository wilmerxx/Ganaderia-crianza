package com.grupo1.ganaderiagrupo1.Modelos;

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
        private String fechaControl;
        private String observaciones;
        private String ganado_id;
        private String estado;
}
