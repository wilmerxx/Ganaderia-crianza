package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
<<<<<<< HEAD
=======

import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;

>>>>>>> 9f232e2 (actualización del merge (#24))
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
