package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Servicios.AreaService;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private GanadoServicio ganadoServicio;

    @GetMapping
    public ResponseEntity<?> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        if (areaService.getAllAreas().isEmpty()) {
            Area area = new Area("", "", "", "", 23.34,"");
            return ResponseEntity.ok(area);

        }
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable String id) {
        Area area = areaService.getAreaById(id);
        if (area != null) {
            return  ResponseEntity.ok(area);
        } else {
               return ResponseEntity.badRequest().body("No existe un area con ese id");
        }
    }

    @PostMapping
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        try {

            if(areaService.getAllAreas().contains(area)){
                return ResponseEntity.badRequest().body("Ya existe un area con ese id");
            }
            if (ganadoServicio.buscarPorId(area.getGanado_id()) == null) {
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
    public ResponseEntity<Void> deleteArea(@PathVariable String id) {
        areaService.deleteArea(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
