package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AlimentacionControlador {

    @Autowired
    GanadoServicio alimentacionServicio;

    @GetMapping("/alimentacion")
    public ResponseEntity<?> getAlimentacion(){
        if(alimentacionServicio.listaAlimentacion().isEmpty()){
            return ResponseEntity.ok("No hay alimentacion");
        }else {
            return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
        }
    }

    // traer por id la alimentacion
    @GetMapping("/alimentacion/{id}")
    public ResponseEntity<?> getAlimentacionPorId(){
        if (alimentacionServicio.listaAlimentacion().isEmpty()){
            Date date = new Date();
            Alimentacion alimentacion = new Alimentacion("1","Garrapata", "Garrapata", date, "100");
            return ResponseEntity.ok(alimentacion);
        }
        else {
            return ResponseEntity.ok(alimentacionServicio.listaAlimentacion());
        }
    }

    //guardar
    @PostMapping("/alimentacion")
    public ResponseEntity<?> postAlimentacion(@RequestBody Alimentacion alimentacion){
        if(!alimentacionServicio.listaAlimentacion().contains(alimentacion)){
            alimentacionServicio.guardarAlimentacion(alimentacion);
            return ResponseEntity.ok(alimentacion);
        }else {
            return ResponseEntity.badRequest().body("Ya existe una alimentacion con ese id");
        }
    }

    //actualizar
    @PutMapping("/alimentacion")
    public ResponseEntity<?> putAlimentacion(@RequestBody Alimentacion alimentacion){
        Alimentacion alimentacion1 = alimentacionServicio.buscarAlimentacionPorId(alimentacion.getAlimentacion_id());
        if(Objects.nonNull(alimentacion1)){
            return ResponseEntity.badRequest().body("No existe una alimentacion con ese id");
        }else {
            alimentacionServicio.actualizarAlimentacion(alimentacion);
            return ResponseEntity.ok(alimentacion);
        }
    }
}