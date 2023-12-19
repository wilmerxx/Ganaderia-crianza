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

    public void save(Area area) {
        if (area.getAreaId() == null) {
            area.setAreaId(String.valueOf(nextAreaId));
            nextAreaId++;
        }
        areas.add(area);
    }

    public void deleteById(String id) {
        areas.removeIf(area -> area.getAreaId().equals(id));
    }

    public void updateById(Area updatedArea) {
        areas.removeIf(area -> area.getAreaId().equals(updatedArea.getAreaId()));
        areas.add(updatedArea);
    }
}
