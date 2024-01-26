package com.grupo1.ganaderiagrupo1.Servicios.impl;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlEnfermedadesServicioImpl implements ControlEnfermedadesServicio {
    @Autowired
    private ControlEnfermedadesRepositorio controlEnfermedadesRepositorio;
    @Autowired
    private GanadoServicioImpl ganadoServicio;

    @Override
    public void guardarControlEnfermedades(ControlEnfermedades controlEnfermedades) {

    }

    @Override
    public void actualizarContolEnfermedades(ControlEnfermedades controlEnfermedades) {

    }

    @Override
    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades) {

    }

    @Override
    public List<ControlEnfermedades> listaControlEnfermedades() {
        return null;
    }

    @Override
    public ControlEnfermedades buscarControlEnfermedadesPorId(int id) {
        return null;
    }


    // Control de enfermedades


}
