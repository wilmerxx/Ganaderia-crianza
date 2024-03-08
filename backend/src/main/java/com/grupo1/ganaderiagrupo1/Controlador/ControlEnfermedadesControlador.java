package com.grupo1.ganaderiagrupo1.Controlador;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;
import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/controlEnfermedades")
public class ControlEnfermedadesControlador {
    @Autowired
    ControlEnfermedadesServicio controlEnfermedadesServicio;

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getControlEnfermedades(){
      try{
          return ResponseEntity.ok(controlEnfermedadesServicio.listaControlEnfermedades());
      }catch (ResourceNotFoundException e){
          return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

    @GetMapping("/estados/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getControlEnfermedadesPorEstado(@PathVariable String estado){
      try{
          return ResponseEntity.ok(controlEnfermedadesServicio.listaControlEnfermedadesPorEstado(estado));
      }catch (ResourceNotFoundException e){
          return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> postControlEnfermedades(@RequestBody @Valid ControlNuevoDto controlEnfermedades){
        try{
            controlEnfermedadesServicio.guardarControlEnfermedades(controlEnfermedades);
            return ResponseEntity.ok(new MensajeExito(new Date(), "Control de enfermedades guardado exitosamente", HttpStatus.OK ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }

    // traer por id el control de enfermedades
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getControlEnfermedadesPorId(@PathVariable int id){
        try{
            return ResponseEntity.ok(controlEnfermedadesServicio.buscarControlEnfermedadesPorId(id));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
        }
    }


    //actualizar control de enfermedades
    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> putControlEnfermedades(@RequestBody @Valid ControlExisteDto controlEnfermedades){
      try{
          controlEnfermedadesServicio.actualizarContolEnfermedades(controlEnfermedades);
          return ResponseEntity.ok(controlEnfermedades);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

    //actualizar estado control de enfermedades
    @PutMapping("/{id}/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> putEstadoControlEnfermedades(@PathVariable int id, @PathVariable String estado){
      try{
          controlEnfermedadesServicio.actualizarEstadoControlEnfermedades(id, estado);
          return ResponseEntity.ok(new MensajeExito(new Date(), "Estado actualizado exitosamente", HttpStatus.OK ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

    //eliminar control de enfermedades
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteControlEnfermedades(@PathVariable int id){
      try{
          controlEnfermedadesServicio.eliminarControlEnfermedades(id);
          return ResponseEntity.ok(new MensajeExito(new Date(), "Control de enfermedades eliminado exitosamente", HttpStatus.OK ));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(new ApiError(new Date(), e.getCode(), e.getMessage(), e.getStatus()));
      }
    }

}
