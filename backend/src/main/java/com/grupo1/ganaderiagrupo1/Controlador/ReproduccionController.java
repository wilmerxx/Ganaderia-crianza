package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import com.grupo1.ganaderiagrupo1.Servicios.ReproduccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reproducciones")
public class ReproduccionController {

    private final ReproduccionService reproduccionService;

    public ReproduccionController(ReproduccionService reproduccionService) {
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
            reproduccion.setReproduccion_id(UUID.randomUUID().toString());
            reproduccionService.agregarReproduccion(reproduccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reproducción agregada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reproduccion> buscarPorId(@PathVariable String id) {
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
    public ResponseEntity<String> eliminarReproduccion(@PathVariable String id) {
        try {
            reproduccionService.eliminarReproduccion(id);
            return ResponseEntity.ok("Reproducción eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Otros métodos del controlador según sea necesario
}
