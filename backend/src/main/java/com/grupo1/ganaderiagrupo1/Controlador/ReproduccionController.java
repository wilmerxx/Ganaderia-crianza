package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reproducciones")
public class ReproduccionController {

    private final ReproduccionService reproduccionService;

    public ReproduccionController(ReproduccionService reproduccionService) {
        this.reproduccionService = reproduccionService;
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Reproduccion>> listarTodos() {
        List<Reproduccion> reproducciones = reproduccionService.buscarTodos();
        return ResponseEntity.ok(reproducciones);
    }
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarReproduccion(@RequestBody Reproduccion reproduccion) {
        try {
            reproduccionService.agregarReproduccion(reproduccion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reproducción agregada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<Reproduccion> buscarPorId(@PathVariable String id) {
        Reproduccion reproduccion = reproduccionService.buscarPorId(id);
        if (reproduccion != null) {
            return ResponseEntity.ok(reproduccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarReproduccion(@RequestBody Reproduccion reproduccion) {
        try {
            reproduccionService.actualizarReproduccion(reproduccion);
            return ResponseEntity.ok("Reproducción actualizada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
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
