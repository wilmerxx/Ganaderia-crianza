package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.AreaRepository;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private GanadoRepositorio ganadoRepositorio;

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }
    public void addArea(Area area) {
        areaRepository.save(area);
        ganadoRepositorio.buscarPorId(area.getGanado().getGanado_id()).getAreas().add(area);

    }

    public Area getAreaById(String id) {
        return areaRepository.findById(id);
    }

    public void updateArea(Area updatedArea) {
         areaRepository.updateById(updatedArea);
         Ganado ganado = ganadoRepositorio.buscarPorId(updatedArea.getGanado().getGanado_id());
         ganado.getAreas().removeIf(area -> area.getAreaId().equals(updatedArea.getAreaId()));
         ganado.getAreas().add(updatedArea);
    }
    public void deleteArea(String id) {
        areaRepository.deleteById(id);
    }


}
