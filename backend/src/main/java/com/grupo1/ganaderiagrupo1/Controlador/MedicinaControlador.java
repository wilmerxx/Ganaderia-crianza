package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Servicios.MedicinaServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.MedicinaServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicina")
public class MedicinaControlador {
    @Autowired
    MedicinaServicio medicinaServicio;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getMedicinas(){
        try{
            return ResponseEntity.ok(medicinaServicio.listaMedicina());
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @GetMapping("/estados/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getMedicinaPorEstado(@PathVariable String estado){
        try{
            return ResponseEntity.ok(medicinaServicio.listaMedicinaPorEstado(estado));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    // traer por id la medicina
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getMedicinaPorId(@PathVariable int id){
        try{
            return ResponseEntity.ok(medicinaServicio.buscarMedicinaPorId(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> postMedicina(@RequestBody @Valid MedicinaNuevoDto medicina){
        try{
            medicinaServicio.guardarMedicina(medicina);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Medicina guardada exitosamente", HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(),e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    //actualizar estado medicina
    @PutMapping("/{id}/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> putMedicinaEstado(@PathVariable int id, @PathVariable String estado){
        try{
            medicinaServicio.actualizarEstadoMedicina(id, estado);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Estado de medicina actualizado exitosamente", HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    //actualizar medicina OJO
    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> putMedicina(@RequestBody @Valid MedicinaExisteDto medicina){
        try{
            medicinaServicio.actualizarMedicina(medicina);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Medicina actualizada exitosamente", HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMedicina(@PathVariable int id){
        try{
            medicinaServicio.elimnarMedicina(id);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Medicina eliminada exitosamente", HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }
}
