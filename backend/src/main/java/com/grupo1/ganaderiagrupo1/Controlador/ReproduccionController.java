package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Servicios.ReproduccionServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.ReproduccionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/reproducciones")
public class ReproduccionController {

    @Autowired
    ReproduccionServicio reproduccionService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(reproduccionService.buscarTodos());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }
    @GetMapping("/estados/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        try {
            return ResponseEntity.ok(reproduccionService.buscarPorEstado(estado));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            return ResponseEntity.ok(reproduccionService.buscarPorId(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @PutMapping("/{id}/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> cambiarEstado(@PathVariable int id, @PathVariable String estado) {
        try {
            reproduccionService.actualizarEstado(id, estado);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Estado actualizado correctamente", HttpStatus.OK));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> agregarReproduccion(@RequestBody ReproduccionNuevoDto reproduccion) {
        try {
            reproduccionService.agregarReproduccion(reproduccion);
            return ResponseEntity.badRequest().body(new MensajeExito(new Date(), "Reproducción agregada correctamente", HttpStatus.CREATED));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> actualizarReproduccion(@RequestBody ReproduccionExisteDto reproduccion) {
        try {
            reproduccionService.actualizarReproduccion(reproduccion);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Reproducción actualizada correctamente", HttpStatus.OK));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> eliminarReproduccion(@PathVariable int id) {
        try {
            reproduccionService.eliminarReproduccion(id);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Reproducción eliminada correctamente", HttpStatus.OK));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }
}
