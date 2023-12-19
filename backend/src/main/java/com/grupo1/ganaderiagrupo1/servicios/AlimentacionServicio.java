package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import com.grupo1.ganaderiagrupo1.Repositorios.AlimentacionRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentacionServicio {
    @Autowired
    private GanadoRepositorio ganadoRepositorio;
    @Autowired
    private AlimentacionRepositorio alimentacionRepositorio;

    public void guardarAlimentacion(Alimentacion alimentacion) {
        alimentacionRepositorio.guardar(alimentacion);
        ganadoRepositorio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().add(alimentacion);
    }

    public void actualizarAlimentacion(Alimentacion alimentacion) {
        ganadoRepositorio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().removeIf(alimentacion1 -> alimentacion1.getAlimentacion_id().equals(alimentacion.getAlimentacion_id()));
        ganadoRepositorio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().add(alimentacion);
        alimentacionRepositorio.actualizar(alimentacion);
    }

    public List<Alimentacion> listaAlimentacion() {
        return alimentacionRepositorio.buscarTodos();
    }

    public void eliminarAlimentacion(Alimentacion alimentacion) {
        ganadoRepositorio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().remove(alimentacion);
    }

    public Alimentacion buscarAlimentacionPorId(String id) {
        return alimentacionRepositorio.buscarPorId(id);
    }

    public Alimentacion buscarAlimentacionPorIdGanado(String id) {
        return ganadoRepositorio.buscarPorId(id).getAlimentaciones().stream().filter(alimentacion -> alimentacion.getGanado_id().equals(id)).findFirst().get();
    }

}
