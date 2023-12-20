package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
<<<<<<< HEAD
import com.grupo1.ganaderiagrupo1.Servicios.AreaService;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
=======
>>>>>>> a26df49 (clase reproduccion y areav2)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:4200")
=======
>>>>>>> a26df49 (clase reproduccion y areav2)
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

<<<<<<< HEAD
    @Autowired
    private GanadoServicio ganadoServicio;

    @GetMapping
    public ResponseEntity<?> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        if (areaService.getAllAreas().isEmpty()) {
            Area area = new Area("", "", "", "", 23.34, "");
            return ResponseEntity.ok(area);

        }
=======
    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
>>>>>>> a26df49 (clase reproduccion y areav2)
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> getAreaById(@PathVariable String id) {
        Area area = areaService.getAreaById(id);
        if (area != null) {
            return ResponseEntity.ok(area);
        } else {
            return ResponseEntity.badRequest().body("No existe un area con ese id");
=======
    public ResponseEntity<Area> getAreaById(@PathVariable String id) {
        Area area = areaService.getAreaById(id);
        if (area != null) {
            return new ResponseEntity<>(area, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
>>>>>>> a26df49 (clase reproduccion y areav2)
        }
    }

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<?> addArea(@RequestBody Area area) {
        try {

            if (areaService.getAllAreas().contains(area)) {
                return ResponseEntity.badRequest().body("Ya existe un area con ese id");
            }
            if (ganadoServicio.buscarPorId(area.getGanado_id()) == null) {
                return ResponseEntity.badRequest().body("No existe un ganado con ese id");
            }
            areaService.addArea(area);
            return new ResponseEntity<>("Area creada", HttpStatus.CREATED);
=======
    public ResponseEntity<Area> addArea(@RequestBody Area area) {
        try {
            Area nuevaArea = areaService.addArea(area);
            return new ResponseEntity<>(nuevaArea, HttpStatus.CREATED);
>>>>>>> a26df49 (clase reproduccion y areav2)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

<<<<<<< HEAD
    @PutMapping()
    public ResponseEntity<Area> updateArea(@RequestBody Area updatedArea) {
        try {
            areaService.updateArea(updatedArea);
            if (updatedArea != null) {
                return new ResponseEntity<>(updatedArea, HttpStatus.OK);
=======
    @PutMapping("/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable String id, @RequestBody Area updatedArea) {
        try {
            Area area = areaService.updateArea(id, updatedArea);
            if (area != null) {
                return new ResponseEntity<>(area, HttpStatus.OK);
>>>>>>> a26df49 (clase reproduccion y areav2)
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

<<<<<<< HEAD
=======
    @PostMapping("/{areaId}/ganados")
    public ResponseEntity<Area> assignGanadoToArea(@PathVariable String areaId, @RequestBody Ganado ganado) {
        try {
            Area area = areaService.assignGanadoToArea(areaId, ganado);
            if (area != null) {
                return new ResponseEntity<>(area, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
>>>>>>> a26df49 (clase reproduccion y areav2)
}
