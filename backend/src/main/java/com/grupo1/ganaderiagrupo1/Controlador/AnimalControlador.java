package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Modelos.animal.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimalControlador {
    List<Animal> animales = new ArrayList<>(
            List.of(
                    new Animal(2, "Toro", "Macho", "Ho", "dskfj", "sdf", "sdf", "sdf", "sdf", "sdf", 3),
                    new Animal(2, "Toro", "Macho", "Ho", "dskfj", "sdf", "sdf", "sdf", "sdf", "sdf", 3),
                    new Animal(3, "Vaca", "Hembra", "Ho", "dskfj", "sdf", "sdf", "sdf", "sdf", "sdf", 3)
            )
    );

    @GetMapping("/animal")
    public ResponseEntity<?> getAnimal(){
        return ResponseEntity.ok(animales);
    }
}
