package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import com.grupo1.ganaderiagrupo1.Repositorios.ReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReproduccionService {

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private GanadoServicio ganadoServicio;

    public void agregarReproduccion(Reproduccion reproduccion) {
        reproduccionRepository.agregar(reproduccion);
        ganadoServicio.buscarPorId(reproduccion.getGanado().getGanado_id()).getReproducciones().add(reproduccion);
    }
    public void eliminarReproduccion(String id) {
        reproduccionRepository.eliminar(id);
    }

    public void actualizarReproduccion(Reproduccion o) {
        Ganado ganado = ganadoServicio.buscarPorId(o.getGanado().getGanado_id());
        ganado.getReproducciones().removeIf(reproduccion -> reproduccion.getReproduccion_id().equals(o.getReproduccion_id()));
        ganado.getReproducciones().add(o);
        reproduccionRepository.actualizar(o);
    }
    public Reproduccion buscarPorId(String id) {
        return reproduccionRepository.buscarPorId(id);
    }
    public List<Reproduccion> buscarTodos() {
        return reproduccionRepository.buscarTodos();
    }

}
