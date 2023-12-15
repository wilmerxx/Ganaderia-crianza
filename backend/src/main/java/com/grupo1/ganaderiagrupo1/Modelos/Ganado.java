package com.grupo1.ganaderiagrupo1.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Ganado {
    private int id;
    private String nombre;
    private String raza;
    private String sexo;
    private double peso;
    private String fechaNacimiento;
    private String tipo;
    private String observaciones;
    private int idLote;
}
