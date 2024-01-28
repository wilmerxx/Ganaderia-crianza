package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class GanadoControlador {

    @Autowired
    private GanadoServicio ganadoServicio;

    /*|---------------------------|
      |  GET GANADO               |
      |---------------------------|
    */
    @GetMapping("/ganados")
    public ResponseEntity<?> getGanado(){
        try {
            return ResponseEntity.ok(ganadoServicio.buscarTodos());
        }catch (ResourceNotFoundException e){
            ApiError apiError = new ApiError(new Date(),e.getCode(),e.getMessage(),e.getStatus());
            return new ResponseEntity<>(apiError,apiError.getStatus());
        }
    }
    //ganados por estado asc
    @GetMapping("/ganados/estados/{estado}")
    public ResponseEntity<?> getGanadoPorEstadoAsc(@PathVariable String estado){
        try {
            return ResponseEntity.ok(ganadoServicio.gandosPorEstadosAsc(estado));
        }catch (ResourceNotFoundException e){
            ApiError apiError = new ApiError(new Date(),e.getCode(),e.getMessage(),e.getStatus());
            return new ResponseEntity<>(apiError,apiError.getStatus());
        }
    }


    //filtrar por tipo de vaca
    @GetMapping("/ganados/tipo/{tipo}")
    public ResponseEntity<?> getGanadoPorTipo(@PathVariable String tipo){
        List<GanadoDto> ganado = ganadoServicio.buscarPorTipo(tipo);
        if(Objects.isNull(ganado)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese tipo");
        }else {
            return ResponseEntity.ok(ganado);
        }
    }
    // traer por id el ganado
    @GetMapping("/ganados/{id}")
    public ResponseEntity<?> getGanadoPorId(@PathVariable int id){
        GanadoDto ganado = ganadoServicio.buscarPorId(id);
        if(Objects.isNull(ganado)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }else {
            return ResponseEntity.ok(ganado);
        }
    }
    /*|---------------------------|
      |  POST, PUT, DELETE GANADO |
      |---------------------------|
    */

    @PostMapping("/ganados")
    public ResponseEntity<?> postGanado(@RequestBody @Valid GanadoNuevoDto ganado){
        try{
            ganadoServicio.guardar(ganado);
            return ResponseEntity.ok(new ApiError(new Date(),"p-200","Ganado registrado correctamente",HttpStatus.CREATED));
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new ApiError(new Date(),e.getCode(),e.getMessage(),e.getStatus()),e.getStatus());
        } catch (DateTimeParseException e){
            return new ResponseEntity<>(new ApiError(new Date(),"f-23","El formato de la fecha de nacimiento es incorrecto. Debe ser 'yyyy-MM-dd ",HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
        }
    }
    //actualizar ganado
    @PutMapping("/ganados")
    public ResponseEntity<?> putGanado(@RequestBody GanadoExisteDto ganado){
        GanadoDto ganado1 = ganadoServicio.buscarPorId(ganado.getGanado_id());
        if(Objects.isNull(ganado1)){
            return ResponseEntity.badRequest().body("No existe un ganado con ese id");
        }
        ganadoServicio.actualizar(ganado);
        return ResponseEntity.ok(ganado);
    }

    @DeleteMapping("/ganados/{id}")
    public ResponseEntity<?> deleteGanado(@PathVariable int id){
        try{
            ganadoServicio.eliminar(id);
            return ResponseEntity.ok(new ApiError(new Date(),"p-200","Ganado eliminado correctamente",HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new ApiError(new Date(),e.getCode(),e.getMessage(),e.getStatus()),e.getStatus());
        }
    }

    @PutMapping("/ganados/estado/{estado}/{id}")
    public ResponseEntity<?> deleteGanadoPorEstado(@PathVariable String estado,@PathVariable int id){
        try{
            ganadoServicio.actualizarEstado(estado,id);
            return ResponseEntity.ok(new ApiError(new Date(),"p-200","Ganado eliminado correctamente",HttpStatus.OK));
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(new ApiError(new Date(),e.getCode(),e.getMessage(),e.getStatus()),e.getStatus());
        }
    }
}
