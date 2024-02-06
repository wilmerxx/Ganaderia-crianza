package com.grupo1.ganaderiagrupo1.Dto.Ganado;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GanadoDto {
    private int ganado_id;
    private String codigo;
    private String nombre_ganado;
    private String raza;
    private double peso;
    private String sexo;
    private int edad;
    private String fechaNacimiento;
    private String nombre_madre;
    private String nombre_padre;
    private String tipo;
    private String estado;
    private LocalDateTime creado;
    private LocalDateTime modificado;

    public int calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate fechaActual = LocalDate.now();
        Period period = Period.between(fechaNac, fechaActual);
        int edadEnMeses = period.getYears() * 12 + period.getMonths();
        return edadEnMeses;
    }
}
