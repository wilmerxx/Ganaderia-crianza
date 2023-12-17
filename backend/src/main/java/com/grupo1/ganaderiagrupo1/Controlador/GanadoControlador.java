package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GanadoControlador {

    List<Ganado> animales = new ArrayList<>(
            List.of(
                    new Ganado(1, "torivio", "rose", "macho",278.3,"13/9/2023", "vaca", "esta en buen estado",3),
                    new Ganado(1, "martita", "rose", "hembra",28.3,"18/9/2021", "vaca", "esta con garapatas",2),
                    new Ganado(1, "josefa", "rose", "hembra",78.3,"13/9/2020", "vaca", "esta en enferma",3)
            )
    );


    @GetMapping("/ganados")
    public ResponseEntity<?> getGanado(){
        return ResponseEntity.ok("");

    }
}
