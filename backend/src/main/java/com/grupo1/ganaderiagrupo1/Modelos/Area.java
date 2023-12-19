package com.grupo1.ganaderiagrupo1.Modelos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Area {

    private String areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
    // Corregir el nombre de la lista y el tipo de dato
    private List<Ganado> ganados = new ArrayList<>();
}

