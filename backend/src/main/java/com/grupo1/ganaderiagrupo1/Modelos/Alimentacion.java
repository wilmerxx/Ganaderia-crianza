package com.grupo1.ganaderiagrupo1.Modelos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Alimentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alimentacion_id;
    private String nombre_suplemento;
    private int cantidad_suplemento;
    private String fecha_alimentacion;
    private String estado;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ganado_id", referencedColumnName = "ganado_id")
    private Ganado ganado;
    @CreatedDate
    private LocalDateTime creado;
    @LastModifiedDate
    private LocalDateTime modificado;
}
