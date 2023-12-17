package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class GanadoControlador {

    @Autowired
    private GanadoServicio ganadoServicio;
    @GetMapping("/ganados")
    public ResponseEntity<?> getGanado(){
        if(ganadoServicio.buscarTodos().isEmpty()){
            Date date = new Date();
            Ganado ganado = new Ganado( "String ganado_id", "String codigo"," String nombre_ganado", "String raza", 345.34, "String sexo", date,"String tipo");
            return ResponseEntity.ok(ganado);
        }else {
            return ResponseEntity.ok(ganadoServicio.buscarTodos());
        }

    }

    @PostMapping("/ganados")
    public ResponseEntity<?> postGanado(@RequestBody Ganado ganado){
        Ganado ganado1 = (Ganado) ganadoServicio.buscarPorId(ganado.getGanado_id());
        if(!Objects.isNull(ganado1)){
            return ResponseEntity.badRequest().body("No se puede crear un ganado con un id");
        }
        ganadoServicio.guardar(ganado);
        return ResponseEntity.ok(ganado);
    }
}
