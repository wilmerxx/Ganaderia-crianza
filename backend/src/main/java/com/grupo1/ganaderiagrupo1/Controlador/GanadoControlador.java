package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GanadoControlador {

    @Autowired
    private GanadoServicio ganadoServicio;
    @GetMapping("/ganados")
    public ResponseEntity<?> getGanado(){
        if(ganadoServicio.buscarTodos().isEmpty()){
            Date date = new Date();

            Ganado ganado = new Ganado( "", "","", "", 0.00, "String sexo", date,"String tipo","");

            return ResponseEntity.ok(ganado);
        }else {
            return ResponseEntity.ok(ganadoServicio.buscarTodos());
        }

    }

    @PostMapping("/ganados")
    public ResponseEntity<?> postGanado(@RequestBody Ganado ganado){

        if(ganadoServicio.buscarTodos().contains(ganado)){
            return ResponseEntity.badRequest().body("Ya existe es ganado");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaFormateada = sdf.format(ganado.getFechaNacimiento());
        Date fecha = new Date(fechaFormateada);
        ganado.setFechaNacimiento(fecha);
        ganado.setGanado_id(UUID.randomUUID().toString());

        if(ganado.getMadre_id()!=null){
            ganado.setMadre(ganadoServicio.buscarPorId(ganado.getMadre_id()));
        }if(ganado.getPadre_id()!=null){
            ganado.setPadre(ganadoServicio.buscarPorId(ganado.getPadre_id()));
        }

        ganadoServicio.guardar(ganado);
        return ResponseEntity.ok(ganado);
    }

    // traer por id el ganado
    @GetMapping("/ganados/{id}")
    public ResponseEntity<?> getGanadoPorId(@PathVariable String id){
        Ganado ganado = ganadoServicio.buscarPorId(id);
        if(Objects.isNull(ganado)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }else {
            return ResponseEntity.ok(ganado);
        }
    }

    //actualizar ganado
    @PutMapping("/ganados")
    public ResponseEntity<?> putGanado(@RequestBody Ganado ganado){
        Ganado ganado1 = ganadoServicio.buscarPorId(ganado.getGanado_id());
        if(Objects.isNull(ganado1)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }
        ganadoServicio.actualizar(ganado);
        return ResponseEntity.ok(ganado);


    }

    @DeleteMapping("/ganados/{id}")
    public ResponseEntity<?> deleteGanado(@PathVariable String id){
        Ganado ganado = ganadoServicio.buscarPorId(id);
        if(Objects.isNull(ganado)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }
        ganadoServicio.eliminar(id);
        return ResponseEntity.ok("Ganado eliminado correctamente");
    }
}
