package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AreaRepository {

    private final List<Area> areas = new ArrayList<>();
    private int nextAreaId = 1;

    public List<Area> findAll() {
        return areas;
    }

    public Area findById(String id) {
        return areas.stream()
                .filter(area -> area.getAreaId().equals(id))
                .findFirst()
                .orElse(null);
    }

<<<<<<< HEAD
    public void save(Area area) {
=======
    public Area save(Area area) {
>>>>>>> a26df49 (clase reproduccion y areav2)
        if (area.getAreaId() == null) {
            area.setAreaId(String.valueOf(nextAreaId));
            nextAreaId++;
        }
<<<<<<< HEAD
        areas.add(area);
=======

        areas.add(area);
        return area;
>>>>>>> a26df49 (clase reproduccion y areav2)
    }

    public void deleteById(String id) {
        areas.removeIf(area -> area.getAreaId().equals(id));
    }

<<<<<<< HEAD
    public void updateById(Area updatedArea) {
        areas.removeIf(area -> area.getAreaId().equals(updatedArea.getAreaId()));
        areas.add(updatedArea);
=======
    public Area updateById(String id, Area updatedArea) {
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            if (area.getAreaId().equals(id)) {
                area.setNombreArea(updatedArea.getNombreArea());
                area.setTipoArea(updatedArea.getTipoArea());
                area.setTipoPasto(updatedArea.getTipoPasto());
                area.setSuperficie(updatedArea.getSuperficie());

                return area;
            }
        }
        return null;
    }

    public Area assignGanadoToArea(String areaId, Ganado ganado) {
        Area area = findById(areaId);
        if (area != null) {
            // Verificar si el ganado ya está asignado a otra área
            if (ganado.estaAsignadoAArea() && !ganado.getAreaId().equals(areaId)) {
                throw new RuntimeException("Error: El ganado ya está asignado a otra área.");
            }

            // Agregar el ganado a la lista de ganados del área
            area.getGanados().add(ganado);
            // Asignar el área al ganado
            ganado.setAreaId(areaId);

            return area;
        }
        return null;
>>>>>>> a26df49 (clase reproduccion y areav2)
    }
}
