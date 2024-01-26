package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import com.grupo1.ganaderiagrupo1.Repositorios.ReproduccionRepository;
import com.grupo1.ganaderiagrupo1.Servicios.ReproduccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReproduccionServiceImpl implements ReproduccionServicio {

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private GanadoServicioImpl ganadoServicio;


    @Override
    public void agregarReproduccion(Reproduccion reproduccion) {

    }

    @Override
    public void eliminarReproduccion(int id) {

    }

    @Override
    public void actualizarReproduccion(Reproduccion o) {

    }

    @Override
    public Reproduccion buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Reproduccion> buscarTodos() {
        return null;
    }
}
