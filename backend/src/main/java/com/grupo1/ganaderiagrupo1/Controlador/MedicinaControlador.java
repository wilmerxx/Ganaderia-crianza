package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Servicios.MedicinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class MedicinaControlador {
    @Autowired
    MedicinaServicio medicinaServicio;

    @GetMapping("/medicina")
    public ResponseEntity<?> getMedicina(){
        if(medicinaServicio.listaMedicina().isEmpty()){
            Date date = new Date();
            Medicina medicina = new Medicina("", "", "", "", date, "");
            return ResponseEntity.ok(medicina);
        }else {

            return ResponseEntity.ok(medicinaServicio.listaMedicina());

        }
    }

    @PostMapping("/medicina")
    public ResponseEntity<?> postMedicina(Medicina medicina){

        if(!medicinaServicio.listaMedicina().contains(medicina)){
            medicinaServicio.guardarMedicina(medicina);
            return ResponseEntity.ok(medicina);

        }else {
            return ResponseEntity.badRequest().body("Ya existe una medicina con ese id");
        }
    }

    // traer por id la medicina
    @GetMapping("/medicina/{id}")
    public ResponseEntity<?> getMedicinaPorId(String id){
        Medicina medicina = medicinaServicio.buscarMedicinaPorId(id);
        if(medicinaServicio.listaMedicina().contains(medicina)){
            return ResponseEntity.ok(medicina);
        }else {
            return ResponseEntity.badRequest().body("No existe una medicina con ese id");
        }
    }

    //actualizar medicina OJO
    @PutMapping("/medicina")
    public ResponseEntity<?> putMedicina(Medicina medicina){
        if(medicinaServicio.listaMedicina().contains(medicina)){
            medicinaServicio.actualizarMedicina(medicina);
            return ResponseEntity.ok(medicina);
        }else {
            return ResponseEntity.badRequest().body("No existe una medicina con ese id");
        }
    }

}
