package com.grupo1.ganaderiagrupo1.Modelos.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaca extends Animal {
    private String fechaParto;
    public Vaca(String fechaParto, int id, String sexo, String fechaNacimiento, String raza, String estado, String fechaVenta, int idMadre, int idPadre) {
        super();
        this.fechaParto = fechaParto;
    }

}
