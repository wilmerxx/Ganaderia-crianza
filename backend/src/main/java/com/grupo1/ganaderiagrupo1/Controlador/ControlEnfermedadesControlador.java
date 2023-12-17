package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class ControlEnfermedadesControlador {
    @Autowired
    ControlEnfermedadesServicio controlEnfermedadesServicio;

    @GetMapping("/controlEnfermedades")
    public ResponseEntity<?> getControlEnfermedades(){
        if(11>10){
            Date date = new Date();
            ControlEnfermedades controlEnfermedades = new ControlEnfermedades("String control_id", "String enfermedad", 239.4, date,"String control_id", "String enfermedad", "String estado");
            return ResponseEntity.ok(controlEnfermedades);
        }else {
            return ResponseEntity.ok("No hay control de enfermedades");
        }
    }

    @PostMapping("/controlEnfermedades")
    public ResponseEntity<?> postControlEnfermedades(@RequestBody ControlEnfermedades controlEnfermedades){

        return ResponseEntity.ok(controlEnfermedadesServicio);
    }
}
