package com.grupo1.ganaderiagrupo1.Modelos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Ganado {
    @Id
    private String ganado_id;
    private String codigo;
    private String nombre_ganado;
    private String raza;
    private double peso;
    private String sexo;
    private String fechaNacimiento;
    private String tipo;
    @ManyToOne()
    @JoinColumn(name = "ganado_madre_id" , referencedColumnName = "ganado_id")
    private Ganado madre;
    @ManyToOne()
    @JoinColumn(name = "ganado_padre_id" , referencedColumnName = "ganado_id")
    private Ganado padre;
    // estado = muerto, vivo, enfermo, sano
    private String estado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    @OneToMany(mappedBy = "ganado", cascade = CascadeType.ALL)
    public List<ControlEnfermedades> controlEnfermedades = new ArrayList<>();
    @OneToMany(mappedBy = "ganado", cascade = CascadeType.ALL)
    public List<Medicina> medicinas = new ArrayList<>();
    @OneToMany(mappedBy = "ganado", cascade = CascadeType.ALL)
    public List<Alimentacion> alimentaciones = new ArrayList<>();
    @OneToMany(mappedBy = "ganado", cascade = CascadeType.ALL)
    public List<Area> areas = new ArrayList<>();
    @OneToMany(mappedBy = "ganado", cascade = CascadeType.ALL)
    public List<Reproduccion> reproducciones = new ArrayList<>();
}
