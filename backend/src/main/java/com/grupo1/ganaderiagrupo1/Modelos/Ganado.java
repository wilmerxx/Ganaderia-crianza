package com.grupo1.ganaderiagrupo1.Modelos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String fechaNacimiento;
    private String tipo;
    private String madre_id;
    private String padre_id;
    private Ganado madre;
    private Ganado padre;
    // estado = muerto, vivo, enfermo, sano
    private String estado;
    public List<ControlEnfermedades> controlEnfermedades = new ArrayList<>();
    public List<Medicina> medicinas = new ArrayList<>();
    public List<Alimentacion> alimentaciones = new ArrayList<>();
    public List<Area> areas = new ArrayList<>();
    public List<Reproduccion> reproducciones = new ArrayList<>();

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

    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, String fechaNacimiento, String tipo, String estado) {
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
        this.estado = estado;
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
     * @param madre_id
     * @param padre_id
     * @param madre
     * @param padre
     * TODO: Agregar general
     */

    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, String fechaNacimiento, String tipo, String madre_id, String padre_id, Ganado madre, Ganado padre, String estado) {
        this.ganado_id = ganado_id;
        this.codigo = codigo;
        this.nombre_ganado = nombre_ganado;
        this.raza = raza;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.madre_id = madre_id;
        this.padre_id = padre_id;
        this.madre = madre;
        this.padre = padre;
        this.estado = estado;
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
    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, String fechaNacimiento, String tipo, Ganado madre, Ganado padre) {
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
     * @param controlEnfermedades
     */
    public Ganado(String ganado_id, String codigo, String nombre_ganado, String raza, double peso, String sexo, String fechaNacimiento, String tipo, Ganado madre, Ganado padre, List<ControlEnfermedades> controlEnfermedades) {
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
        this.controlEnfermedades = controlEnfermedades;
    }


}
