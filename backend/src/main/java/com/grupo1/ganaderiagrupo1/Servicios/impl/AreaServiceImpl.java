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
        List<Area> areas = areaRepository.todosAreas();
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for (Area area : areas) {
           for(Ganado ganado : ganados){
               if(area.getGanado().getGanado_id()== ganado.getGanado_id()){
                     AreaDto areaDto = new AreaDto();
                     areaDto.setAreaId(area.getAreaId());
                     areaDto.setNombreArea(area.getNombreArea());
                     areaDto.setSuperficie(area.getSuperficie());
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
    public List<AreaDto> getAreasByEstado(String estado) {
        if (areaRepository.areaPorEstadosAsc(estado).isEmpty()) {
            throw new RuntimeException("No hay areas registradas");
        }
        List<AreaDto> areasDto = new ArrayList<>();
        List<Area> areas = areaRepository.areaPorEstadosAsc(estado);
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for (Area area : areas) {
            for(Ganado ganado : ganados){
                if(area.getGanado().getGanado_id()== ganado.getGanado_id()){
                    AreaDto areaDto = new AreaDto();
                    areaDto.setAreaId(area.getAreaId());
                    areaDto.setNombreArea(area.getNombreArea());
                    areaDto.setSuperficie(area.getSuperficie());
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
        Optional<Area> area = areaRepository.findById(id);
        if (area.isEmpty()) {
            throw new RuntimeException("No existe el area con el id: " + id);
        }
        AreaDto areaDto = new AreaDto();
        areaDto.setAreaId(area.get().getAreaId());
        areaDto.setNombreArea(area.get().getNombreArea());
        areaDto.setSuperficie(area.get().getSuperficie());
        areaDto.setCodigoGanado(area.get().getGanado().getCodigo());
        areaDto.setTipoArea(area.get().getTipoArea());
        areaDto.setTipoPasto(area.get().getTipoPasto());
        areaDto.setNombreGanado(area.get().getGanado().getNombre_ganado());
        areaDto.setEstado(area.get().getEstado());
        areaDto.setCreado(area.get().getCreado());
        areaDto.setModificado(area.get().getModificado());
        return areaDto;
    }

    @Override
    public void updateArea(AreaExisteDto updatedArea) {
        Optional<Area> area = areaRepository.findById(updatedArea.getAreaId());
        if (area.isEmpty()) {
            throw new RuntimeException("No existe el area con el id: " + updatedArea.getAreaId());
        }
        Optional<Ganado> ganado = ganadoRepositorio.findById(updatedArea.getGanadoId());
        if (ganado.isEmpty()) {
            throw new RuntimeException("No existe el ganado con el id: " + updatedArea.getGanadoId());
        }
        area.get().setNombreArea(updatedArea.getNombreArea());
        area.get().setTipoArea(updatedArea.getTipoArea());
        area.get().setTipoPasto(updatedArea.getTipoPasto());
        area.get().setSuperficie(updatedArea.getSuperficie());
        area.get().setEstado(updatedArea.getEstado());
        area.get().setGanado(ganado.get());
        areaRepository.save(area.get());
    }

    @Override
    public void deleteArea(int id) {
        Optional<Area> area = areaRepository.findById(id);
        if (area.isEmpty()) {
            throw new RuntimeException("No existe el area con el id: " + id);
        }
        area.get().setEstado("Inactivo");
        areaRepository.save(area.get());
    }

    @Override
    public void acutualizarEstadoArea(int id, String estado) {
        Optional<Area> area = areaRepository.findById(id);
        if (area.isEmpty()) {
            throw new RuntimeException("No existe el area con el id: " + id);
        }
        area.get().setEstado(estado);
        areaRepository.save(area.get());
    }
}
