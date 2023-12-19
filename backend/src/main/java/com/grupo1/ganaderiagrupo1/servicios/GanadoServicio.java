package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;


import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;

import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GanadoServicio {

    @Autowired
    private GanadoRepositorio ganadoRepositorio;

    public List<Ganado> buscarTodos() {
        return ganadoRepositorio.buscarTodos();
    }
    public void guardar(Ganado o) {
        ganadoRepositorio.guardar(o);
    }

    public void cambiarEstado(Ganado o) {
        ganadoRepositorio.cambiarEstado(o);
    }

    public void actualizar(Ganado o) {
        ganadoRepositorio.actualizar(o);

    }

    public Ganado buscarPorId(String id) {
        return ganadoRepositorio.buscarPorId(id);
    }
}
