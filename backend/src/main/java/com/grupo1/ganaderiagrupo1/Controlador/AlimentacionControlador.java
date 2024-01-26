package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.AlimentacionServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AlimentacionControlador {

    @Autowired
    AlimentacionServicioImpl alimentacionServicio;
    @Autowired
    GanadoServicio ganadoServicio;

    @GetMapping("/alimentacion")
    public ResponseEntity<?> getAlimentacion(){
       try{
           return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
         }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));

       }
    }

    // traer por id la alimentacion
    @GetMapping("/alimentacion/{id}")
    public ResponseEntity<?> getAlimentacionPorId(@PathVariable int id){
       try{
              return ResponseEntity.ok(alimentacionServicio.buscarAlimentacionPorId(id));
            }catch (ResourceNotFoundException e){
              return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    //guardar
    @PostMapping("/alimentacion")
    public ResponseEntity<?> postAlimentacion(@RequestBody AlimentacionNuevoDto alimentacion){

       try{
           alimentacionServicio.guardarAlimentacion(alimentacion);
           return ResponseEntity.ok(alimentacion);
         }catch (ResourceNotFoundException e){
           return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
       }
    }

    //actualizar
    @PutMapping("/alimentacion")
    public ResponseEntity<?> putAlimentacion(@RequestBody AlimentacionExisteDto alimentacion){
        try{
            alimentacionServicio.actualizarAlimentacion(alimentacion);
            return ResponseEntity.ok(alimentacion);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-a12",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

}
