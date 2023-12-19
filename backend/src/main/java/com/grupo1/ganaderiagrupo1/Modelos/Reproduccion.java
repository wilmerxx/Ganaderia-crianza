package com.grupo1.ganaderiagrupo1.Modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reproduccion {
    private String reproduccion_id;
    private Date fecha_parto;
    private String estado_salud;
    private String numero_crias;
    private List<Ganado> ganados;

}
