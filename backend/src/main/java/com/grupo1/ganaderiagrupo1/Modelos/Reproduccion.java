package com.grupo1.ganaderiagrupo1.Modelos;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;
=======
>>>>>>> a26df49 (clase reproduccion y areav2)
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
<<<<<<< HEAD
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fecha_parto;
    private String estado_salud;
    private String numero_crias;
    private String ganado_id;
=======
    private Date fecha_parto;
    private String estado_salud;
    private String numero_crias;
    private List<Ganado> ganados;
>>>>>>> a26df49 (clase reproduccion y areav2)

}
