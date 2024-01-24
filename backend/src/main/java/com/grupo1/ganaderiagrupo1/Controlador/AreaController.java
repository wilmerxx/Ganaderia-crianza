package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Area;

import com.grupo1.ganaderiagrupo1.Servicios.impl.AreaServiceImpl;
import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaServiceImpl areaService;


    @Autowired
    private GanadoServicioImpl ganadoServicio;

    @GetMapping
    public ResponseEntity<?> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        if (areaService.getAllAreas().isEmpty()) {
            return ResponseEntity.ok("No hay areas registradas");

        }

        return new ResponseEntity<>(areas, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable int id) {
        Area area = areaService.getAreaById(id);
        if (area != null) {
            return ResponseEntity.ok(area);
        } else {
            return ResponseEntity.badRequest().body("No existe un area con ese id");
        }
    }


    @PostMapping
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        try {

            if (areaService.getAllAreas().contains(area)) {
                return ResponseEntity.badRequest().body("Ya existe un area con ese id");
            }
            if (ganadoServicio.buscarPorId(area.getGanado().getGanado_id()) == null) {
                return ResponseEntity.badRequest().body("No existe un ganado con ese id");
            }
            areaService.addArea(area);
            return new ResponseEntity<>("Area creada", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Area> updateArea(@RequestBody Area updatedArea) {
        try {
            areaService.updateArea(updatedArea);
            if (updatedArea != null) {
                return new ResponseEntity<>(updatedArea, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable int id) {
        areaService.deleteArea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
