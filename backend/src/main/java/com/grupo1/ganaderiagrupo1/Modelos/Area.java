package com.grupo1.ganaderiagrupo1.Modelos;

import lombok.Data;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
>>>>>>> a26df49 (clase reproduccion y areav2)

import java.util.ArrayList;
import java.util.List;

@Data
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
public class Area {
=======
public class Area {

>>>>>>> a26df49 (clase reproduccion y areav2)
    private String areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
<<<<<<< HEAD
    private String ganado_id;
=======
    // Corregir el nombre de la lista y el tipo de dato
    private List<Ganado> ganados = new ArrayList<>();
>>>>>>> a26df49 (clase reproduccion y areav2)
}

