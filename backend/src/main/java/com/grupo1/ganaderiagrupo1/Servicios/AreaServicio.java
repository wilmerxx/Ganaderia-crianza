package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Dto.Area.AreaDto;
import com.grupo1.ganaderiagrupo1.Dto.Area.AreaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Area.AreaNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import java.util.List;

public interface AreaServicio {
    public List<AreaDto> getAllAreas();
    public List<AreaDto> getAreasByEstado(String estado);
    public void addArea(AreaNuevoDto area);

    public AreaDto getAreaById(int id);

    public void updateArea(AreaExisteDto updatedArea);
    public void deleteArea(int id);

    public void acutualizarEstadoArea(int id, String estado);

}
