package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import java.util.List;

public interface AreaServicio {
    public List<Area> getAllAreas();
    public void addArea(Area area);

    public Area getAreaById(int id);

    public void updateArea(Area updatedArea);
    public void deleteArea(int id);


}
