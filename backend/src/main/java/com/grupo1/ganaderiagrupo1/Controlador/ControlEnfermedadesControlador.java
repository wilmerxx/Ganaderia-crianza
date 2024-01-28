package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;

import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.ControlEnfermedadesServicioImpl;

import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/controlEnfermedades")
public class ControlEnfermedadesControlador {
    @Autowired
    ControlEnfermedadesServicio controlEnfermedadesServicio;
    @Autowired
    GanadoServicio ganadoServicio;

    @GetMapping()
    public ResponseEntity<?> getControlEnfermedades(){
      try{
          return ResponseEntity.ok(controlEnfermedadesServicio.listaControlEnfermedades());
      }catch (ResourceNotFoundException e){
          return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

    @PostMapping()
    public ResponseEntity<?> postControlEnfermedades(@RequestBody ControlNuevoDto controlEnfermedades){
        try{
            controlEnfermedadesServicio.guardarControlEnfermedades(controlEnfermedades);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Control de enfermedades guardado exitosamente", HttpStatus.OK ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    // traer por id el control de enfermedades
    @GetMapping("/{id}")
    public ResponseEntity<?> getControlEnfermedadesPorId(@PathVariable int id){
        try{
            return ResponseEntity.ok(controlEnfermedadesServicio.buscarControlEnfermedadesPorId(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }


    //actualizar control de enfermedades
    @PutMapping()
    public ResponseEntity<?> putControlEnfermedades(@RequestBody ControlExisteDto controlEnfermedades){
      try{
          controlEnfermedadesServicio.actualizarContolEnfermedades(controlEnfermedades);
          return ResponseEntity.ok(controlEnfermedades);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

}
