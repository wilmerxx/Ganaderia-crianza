package com.grupo1.ganaderiagrupo1.Modelos.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaca extends Animal {
    private int idMadre;
    private int idPadre;

}
