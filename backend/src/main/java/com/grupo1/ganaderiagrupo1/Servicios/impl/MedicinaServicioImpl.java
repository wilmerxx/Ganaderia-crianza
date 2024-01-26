package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.MedicinaRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.MedicinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinaServicioImpl implements MedicinaServicio {
    @Autowired
    private MedicinaRepositorio medicinaRepositorio;

    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public void guardarMedicina(Medicina medicina) {

    }

    @Override
    public void actualizarMedicina(Medicina medicina) {

    }

    @Override
    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades) {

    }

    @Override
    public List<Medicina> listaMedicina() {
        return null;
    }

    @Override
    public Medicina buscarMedicinaPorId(int id) {
        return null;
    }
}
