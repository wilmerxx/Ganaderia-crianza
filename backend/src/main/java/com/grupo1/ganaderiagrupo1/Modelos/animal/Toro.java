package com.grupo1.ganaderiagrupo1.Modelos.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Toro extends Animal{
    private String fechaVenta;
    private int idMadre;
    private int idPadre;
}
