package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Modelos.Area;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.AreaRepository;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.AreaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaServicio {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public List<Area> getAllAreas() {
        return null;
    }

    @Override
    public void addArea(Area area) {

    }

    @Override
    public Area getAreaById(int id) {
        return null;
    }

    @Override
    public void updateArea(Area updatedArea) {

    }

    @Override
    public void deleteArea(int id) {

    }
}
