package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import com.grupo1.ganaderiagrupo1.Servicios.impl.AlimentacionServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AlimentacionControlador {

    @Autowired
    AlimentacionServicioImpl alimentacionServicio;
    @Autowired
    GanadoServicio ganadoServicio;

    @GetMapping("/alimentaciones")
    public ResponseEntity<?> getAlimentacion(){
        if(alimentacionServicio.listaAlimentacion().isEmpty()){

            return ResponseEntity.ok("No hay alimentaciones registradas");
        }else {
            return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
        }
    }

    // traer por id la alimentacion
    @GetMapping("/alimentacion/{id}")
    public ResponseEntity<?> getAlimentacionPorId(@PathVariable int id){
        Alimentacion alimentacion = alimentacionServicio.buscarAlimentacionPorId(id);
        if (Objects.isNull(alimentacion)){
            return ResponseEntity.badRequest().body("No existe una alimentacion con ese id");
        }
        else {
            return ResponseEntity.ok(alimentacion);
        }
    }

    //guardar
    @PostMapping("/alimentacion")
    public ResponseEntity<?> postAlimentacion(@RequestBody Alimentacion alimentacion){

        if(alimentacionServicio.listaAlimentacion().contains(alimentacion)){
            return ResponseEntity.badRequest().body("Ya existe una alimentacion con ese id");
        }
        if(Objects.isNull(ganadoServicio.buscarPorId(alimentacion.getGanado().getGanado_id()))){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }
        alimentacionServicio.guardarAlimentacion(alimentacion);
        return ResponseEntity.ok(alimentacion);
    }

    //actualizar
    @PutMapping("/alimentacion")
    public ResponseEntity<?> putAlimentacion(@RequestBody Alimentacion alimentacion){

        if(alimentacionServicio.listaAlimentacion().contains(alimentacion)){
            return ResponseEntity.badRequest().body("No existe una alimentacion con ese id");
        }
        alimentacionServicio.actualizarAlimentacion(alimentacion);
        return ResponseEntity.ok(alimentacion);
    }

}
