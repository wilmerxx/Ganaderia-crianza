package com.grupo1.ganaderiagrupo1.Modelos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class Ganado {
    private String ganado_id;
    private String codigo;
    private String nombre_ganado;
    private String raza;
    private double peso;
    private String sexo;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaNacimiento;
    private String tipo;
    private Ganado madre;
    private Ganado padre;
    private String estado;
    @Setter
    @Getter
    public static List<ControlEnfermedades> controlEnfermedades;

    public Ganado() {
    }

    /**
     * @param ganado_id
     * @param codigo
     * @param nombre_ganado
     * @param raza
     * @param peso
     * @param sexo
     * @param fechaNacimiento
     * @param tipo
     * TODO: Agregar vaca o toro
     */
    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, Date fechaNacimiento, String tipo) {
        this.ganado_id = ganado_id;
        this.codigo = codigo;
        this.nombre_ganado = nombre_ganado;
        this.raza = raza;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.madre = null;
        this.padre = null;
    }

    /**
     * @param ganado_id
     * @param codigo
     * @param nombre_ganado
     * @param raza
     * @param peso
     * @param sexo
     * @param fechaNacimiento
     * @param tipo
     * @param madre
     * @param padre
     * TODO: Agregar ternero
     */
    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, Date fechaNacimiento, String tipo, Ganado madre, Ganado padre) {
        this.ganado_id = ganado_id;
        this.codigo = codigo;
        this.nombre_ganado = nombre_ganado;
        this.raza = raza;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.madre = madre;
        this.padre = padre;
    }
}
