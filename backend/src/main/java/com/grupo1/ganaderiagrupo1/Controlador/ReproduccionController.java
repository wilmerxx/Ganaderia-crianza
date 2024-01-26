package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import com.grupo1.ganaderiagrupo1.Servicios.impl.ReproduccionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reproducciones")
public class ReproduccionController {

    private final ReproduccionServiceImpl reproduccionService;

    public ReproduccionController(ReproduccionServiceImpl reproduccionService) {
        this.reproduccionService = reproduccionService;
    }

    @GetMapping()
    public ResponseEntity<?> listarTodos() {
        if(reproduccionService.buscarTodos().isEmpty()){
            return ResponseEntity.ok("No hay reproducciones registradas");
        }
        return ResponseEntity.ok( reproduccionService.buscarTodos());
    }
    @PostMapping()
    public ResponseEntity<String> agregarReproduccion(@RequestBody Reproduccion reproduccion) {
        try {
            reproduccionService.agregarReproduccion(reproduccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reproducción agregada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reproduccion> buscarPorId(@PathVariable int id) {
        Reproduccion reproduccion = reproduccionService.buscarPorId(id);
        if (reproduccion != null) {
            return ResponseEntity.ok(reproduccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping()
    public ResponseEntity<String> actualizarReproduccion(@RequestBody Reproduccion reproduccion) {
        try {
            reproduccionService.actualizarReproduccion(reproduccion);
            return ResponseEntity.ok("Reproducción actualizada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReproduccion(@PathVariable int id) {
        try {
            reproduccionService.eliminarReproduccion(id);
            return ResponseEntity.ok("Reproducción eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Otros métodos del controlador según sea necesario
}
