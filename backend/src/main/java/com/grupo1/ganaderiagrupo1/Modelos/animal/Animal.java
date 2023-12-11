package com.grupo1.ganaderiagrupo1.Modelos.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Animal {
    private int id;
    private String nombre;
    private String raza;
    private String sexo;
    private String fechaNacimiento;
    private String fechaIngreso;
    private String fechaSalida;
    private String estado;
    private String tipo;
    private String observaciones;
    private int idLote;
}
