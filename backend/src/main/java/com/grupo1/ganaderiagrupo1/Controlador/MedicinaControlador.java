package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Servicios.MedicinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class MedicinaControlador {
    @Autowired
    MedicinaServicio medicinaServicio;

    @Autowired
    GanadoServicio ganadoServicio;

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
    public ResponseEntity<?> postMedicina(@RequestBody Medicina medicina){

        if(medicinaServicio.listaMedicina().contains(medicina)){
            return ResponseEntity.badRequest().body("Ya existe una medicina con ese id");
        }
        if(Objects.isNull(ganadoServicio.buscarPorId(medicina.getGanado_id()))){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }
        medicinaServicio.guardarMedicina(medicina);
        return ResponseEntity.ok(medicina);
    }

    // traer por id la medicina
    @GetMapping("/medicina/{id}")
    public ResponseEntity<?> getMedicinaPorId(@PathVariable String id){
        Medicina medicina = medicinaServicio.buscarMedicinaPorId(id);
        if(medicinaServicio.listaMedicina().contains(medicina)){
            return ResponseEntity.ok(medicina);
        }else {
            return ResponseEntity.badRequest().body("No existe una medicina con ese id");
        }
    }

    //actualizar medicina OJO
    @PutMapping("/medicina")
    public ResponseEntity<?> putMedicina(@RequestBody Medicina medicina){
        if(!medicinaServicio.listaMedicina().contains(medicina)){
            medicinaServicio.actualizarMedicina(medicina);
            return ResponseEntity.ok(medicina);
        }else {
            return ResponseEntity.badRequest().body("No existe una medicina con ese id");
        }
    }

}
