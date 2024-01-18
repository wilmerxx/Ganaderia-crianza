package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;

import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;

import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Objects;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ControlEnfermedadesControlador {
    @Autowired
    ControlEnfermedadesServicio controlEnfermedadesServicio;
    @Autowired
    GanadoServicio ganasoServicio;

    @GetMapping("/controlEnfermedades")
    public ResponseEntity<?> getControlEnfermedades(){
        if(controlEnfermedadesServicio.listaControlEnfermedades().isEmpty()){
            return ResponseEntity.ok("No hay controles de enfermedades registrados");
        }else {

            return ResponseEntity.ok(controlEnfermedadesServicio.listaControlEnfermedades());

        }
    }

    @PostMapping("/controlEnfermedades")
    public ResponseEntity<?> postControlEnfermedades(@RequestBody ControlEnfermedades controlEnfermedades){

        if(!controlEnfermedadesServicio.listaControlEnfermedades().contains(controlEnfermedades)){
            if(Objects.isNull(ganasoServicio.buscarPorId(controlEnfermedades.getGanado().getGanado_id()))){
                return ResponseEntity.badRequest().body("No existe un ganado con ese id");
            }
            controlEnfermedades.setControl_id(UUID.randomUUID().toString());
            controlEnfermedadesServicio.guardarControlEnfermedades(controlEnfermedades);
            return ResponseEntity.ok(controlEnfermedades);

        }else {
            return ResponseEntity.badRequest().body("Ya existe un control de enfermedades con ese id");
        }
    }

    // traer por id el control de enfermedades
    @GetMapping("/controlEnfermedades/{id}")
    public ResponseEntity<?> getControlEnfermedadesPorId(@PathVariable String id){
        ControlEnfermedades controlEnfermedades = controlEnfermedadesServicio.buscarControlEnfermedadesPorId(id);
        if(controlEnfermedadesServicio.listaControlEnfermedades().contains(controlEnfermedades)){
            return ResponseEntity.ok(controlEnfermedades);
        }else {
            return ResponseEntity.badRequest().body("No existe un control de enfermedades con ese id");
        }
    }


    //actualizar control de enfermedades
    @PutMapping("/controlEnfermedades")
    public ResponseEntity<?> putControlEnfermedades(@RequestBody ControlEnfermedades controlEnfermedades){
      ControlEnfermedades controlEnfermedades1 = controlEnfermedadesServicio.buscarControlEnfermedadesPorId(controlEnfermedades.getControl_id());
        if(Objects.isNull(controlEnfermedades1)){
            return ResponseEntity.badRequest().body("No existe un control de enfermedades con ese id");

        }else {
            controlEnfermedadesServicio.actualizarContolEnfermedades(controlEnfermedades);
            return ResponseEntity.ok(controlEnfermedades);
        }
    }

}
