package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Dto.Area.AreaDto;
import com.grupo1.ganaderiagrupo1.Dto.Area.AreaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Area.AreaNuevoDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.AreaRepository;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.AreaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaServicio {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public List<AreaDto> getAllAreas() {
        if (areaRepository.findAll().isEmpty()) {
           throw new RuntimeException("No hay areas registradas");
        }
        List<AreaDto> areasDto = new ArrayList<>();
        List<Area> areas = areaRepository.findAll();
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for (Area area : areas) {
           for(Ganado ganado : ganados){
               if(area.getGanado().getGanado_id()== ganado.getGanado_id()){
                     AreaDto areaDto = new AreaDto();
                     areaDto.setAreaId(area.getAreaId());
                     areaDto.setNombreArea(area.getNombreArea());
                     areaDto.setCodigoGanado(ganado.getCodigo());
                     areaDto.setTipoArea(area.getTipoArea());
                     areaDto.setTipoPasto(area.getTipoPasto());
                     areaDto.setNombreGanado(ganado.getNombre_ganado());
                     areaDto.setEstado(area.getEstado());
                     areaDto.setCreado(area.getCreado());
                     areaDto.setModificado(area.getModificado());
                     areasDto.add(areaDto);
               }
           }
        }
        return areasDto;

    }

    @Override
    public void addArea(AreaNuevoDto area) {

        Optional<Ganado> ganado = ganadoRepositorio.findById(area.getGanadoId());
        if (ganado.isEmpty()) {
            throw new RuntimeException("No existe el ganado con el id: " + area.getGanadoId());
        }
        Area newArea = new Area();
        newArea.setNombreArea(area.getNombreArea());
        newArea.setTipoArea(area.getTipoArea());
        newArea.setTipoPasto(area.getTipoPasto());
        newArea.setSuperficie(area.getSuperficie());
        newArea.setEstado(area.getEstado());
        newArea.setGanado(ganado.get());
        areaRepository.save(newArea);

    }

    @Override
    public AreaDto getAreaById(int id) {
        return null;
    }

    @Override
    public void updateArea(AreaExisteDto updatedArea) {

    }

    @Override
    public void deleteArea(int id) {

    }
}
