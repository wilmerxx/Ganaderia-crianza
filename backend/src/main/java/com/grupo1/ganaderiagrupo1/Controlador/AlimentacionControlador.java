package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Servicios.AlimentacionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import java.util.Date;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AlimentacionControlador {

    @Autowired
    AlimentacionServicio alimentacionServicio;
    @Autowired
    GanadoServicio ganadoServicio;

    @GetMapping("/alimentaciones")
    public ResponseEntity<?> getAlimentacion(){
        if(alimentacionServicio.listaAlimentacion().isEmpty()){
            Date date = new Date();
            Alimentacion alimentacion = new Alimentacion("","", "", date, "");
            return ResponseEntity.ok(alimentacion);
        }else {
            return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
        }
    }

    // traer por id la alimentacion
    @GetMapping("/alimentacion/{id}")
    public ResponseEntity<?> getAlimentacionPorId(@PathVariable String id){
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
        if(Objects.isNull(ganadoServicio.buscarPorId(alimentacion.getGanado_id()))){
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
