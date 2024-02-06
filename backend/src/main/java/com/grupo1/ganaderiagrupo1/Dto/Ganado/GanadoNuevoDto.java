package com.grupo1.ganaderiagrupo1.Dto.Ganado;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class GanadoNuevoDto {
    @NotNull(message = "Codigo no puede ser nulo")
    @Size(min = 2, max = 30, message = "no puede ser menor a 2 letras y no mayor a 30 letras")
    private String codigo;
    @NotNull(message = "Nombre no puede ser nulo")
    @Size(min = 2, max = 30, message = "no puede ser menor a 2 letras y no mayor a 30 letras")
    private String nombre_ganado;
    @Size(min = 2, max = 30, message = "no puede ser menor a 2 letras y no mayor a 30 letras")
    @NotNull(message = "Raza no puede ser nulo")
    private String raza;
    @NotNull(message = "Peso no puede ser nulo")
    //validar que el peso sea mayor a 0
    @Min(value = 20, message = "El peso debe ser mayor a 20 kg")
    @Max(value = 1000, message = "El peso debe ser menor a 1000 kg")
    private double peso;
    @NotNull(message = "Sexo no puede ser nulo")
    private String sexo;
    @NotNull(message = "Fecha de nacimiento no puede ser nulo")
    private String fechaNacimiento;
    private int edad;
    private int ganado_madre_id;
    private int ganado_padre_id;
    private String estado = "Activo";

    public int calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate fechaActual = LocalDate.now();
        Period period = Period.between(fechaNac, fechaActual);
        int edadEnMeses = period.getYears() * 12 + period.getMonths();
        return edadEnMeses;
    }
    public String tipoGanado(String fechaNacimiento, String sexo){
        //calcular edad del ganado en meses
        int edad = 0;
        edad = calcularEdad(fechaNacimiento);
        /*
        Se denomina terneros(as) a los animales hasta los 12 meses de edad,
        vaquillas o torillos desde los 13 hasta los 23 meses de edad
        y vaca o toro a los animales adultos mayores de 24 meses de edad.
         */
        //terneros(as) a los animales hasta los 12 meses de edad y dependiendo del sexo
        if(edad <= 12){
            if(sexo.equals("Macho")){
                return "Ternero";
            }else if(sexo.equals("Hembra")){
                return "Ternera";
            }else {
                throw new IllegalStateException("Sexo indefinido");
            }
        }
        //vaquillas o torillos desde los 13 hasta los 23 meses de edad y dependiendo del sexo
        if(edad >= 13 && edad <= 23){
            if(sexo.equals("Macho")){
                return "Torillo";
            }else if(sexo.equals("Hembra")){
                return "Vaquilla";
            }else {
                throw new IllegalStateException("Sexo indefinido");
            }
        }
        //vaca o toro a los animales adultos mayores de 24 meses de edad y dependiendo del sexo
        if(edad >= 24){
            if(sexo.equals("Macho")){
                return "Toro";
            }else if(sexo.equals("Hembra")){
                return "Vaca";
            }else {
                throw new IllegalStateException("Sexo indefinido");
            }
        }
        return "No se pudo calcular el tipo de ganado";
    }

}
