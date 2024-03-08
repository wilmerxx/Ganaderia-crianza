package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.ErrorDetails;
import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.AlimentacionServicioImpl;

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
@RequestMapping("/api/alimentacion")
public class AlimentacionControlador {

    @Autowired
    AlimentacionServicioImpl alimentacionServicio;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAlimentacion(){
       try{
           return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
         }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));

       }
    }

    // traer por estado la alimentacion
    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAlimentacionPorEstados(@PathVariable String estado){
       try{
           return ResponseEntity.ok(alimentacionServicio.listaAlimentacionPorEstado(estado));
         }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));

       }
    }

    // traer por id la alimentacion
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAlimentacionPorId(@PathVariable int id){
       try{
              return ResponseEntity.ok(alimentacionServicio.buscarAlimentacionPorId(id));
            }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    // lista de total de cantidad de suplemento
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAlimentacionTotalCantidad(){
       try{
              return ResponseEntity.ok(alimentacionServicio.listaAlimentacionTotalCantidad());
            }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    //actualizar estado
    @PutMapping("/{id}/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAlimentacionPorEstado(@PathVariable int id, @PathVariable String estado){
       try{
           alimentacionServicio.actualizarEstadoAlimentacion(id,estado);
           return ResponseEntity.ok(new MensajeExito(new Date(),"Estado actualizado", HttpStatus.OK));
         }catch (ResourceNotFoundException e){
           return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    //guardar
    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> postAlimentacion(@RequestBody AlimentacionNuevoDto alimentacion){

       try{
           alimentacionServicio.guardarAlimentacion(alimentacion);
           return ResponseEntity.ok(new MensajeExito(new Date(),"Alimentacion guardada", HttpStatus.OK));
         }catch (ResourceNotFoundException e){
           return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    //actualizar
    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> putAlimentacion(@RequestBody AlimentacionExisteDto alimentacion){
        try{
            alimentacionServicio.actualizarAlimentacion(alimentacion);
            return ResponseEntity.ok(alimentacion);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    //eliminar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAlimentacion(@PathVariable int id){
        try{
            alimentacionServicio.eliminarAlimentacion(id);
            return ResponseEntity.ok(new MensajeExito(new Date(),"Alimentacion eliminada", HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

}
