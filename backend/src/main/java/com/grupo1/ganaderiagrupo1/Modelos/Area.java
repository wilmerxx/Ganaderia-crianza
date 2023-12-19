package com.grupo1.ganaderiagrupo1.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private String areaId;
    private String nombreArea;
    private String tipoArea;
    private String tipoPasto;
    private double superficie;
    private String ganado_id;

}

