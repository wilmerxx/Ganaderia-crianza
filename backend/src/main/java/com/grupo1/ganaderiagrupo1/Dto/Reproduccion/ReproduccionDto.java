package com.grupo1.ganaderiagrupo1.Dto.Reproduccion;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class ReproduccionDto {
    private int reproduccion_id;
    private String fecha_parto;
    private String estado_parto;
    private String observaciones;
    private int numero_crias;
    private String estado;
    private String codigoGanado;
    private String nombreGanado;
    private LocalDateTime creado;
    private LocalDateTime modificado;
}
