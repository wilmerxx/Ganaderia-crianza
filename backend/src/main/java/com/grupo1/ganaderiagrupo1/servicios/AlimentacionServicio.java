package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import com.grupo1.ganaderiagrupo1.Repositorios.AlimentacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentacionServicio {
    @Autowired
    private GanadoServicio ganadoServicio;
    @Autowired
    private AlimentacionRepositorio alimentacionRepositorio;

    public void guardarAlimentacion(Alimentacion alimentacion) {
        ganadoServicio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().add(alimentacion);
    }

    public void actualizarAlimentacion(Alimentacion alimentacion) {
        Alimentacion alimentacion1 = ganadoServicio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().stream().filter(alimentacion2 -> alimentacion2.getAlimentacion_id().equals(alimentacion.getAlimentacion_id())).findFirst().get();
        alimentacion1.setAlimentacion_id(alimentacion.getAlimentacion_id());
        alimentacion1.setNombre_suplemento(alimentacion.getNombre_suplemento());
        alimentacion1.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
        alimentacion1.setFecha_alimentacion(alimentacion.getFecha_alimentacion());

    }

    public List<Alimentacion> listaAlimentacion() {
        return alimentacionRepositorio.buscarTodos();
    }

    public void eliminarAlimentacion(Alimentacion alimentacion) {
        ganadoServicio.buscarPorId(alimentacion.getGanado_id()).getAlimentaciones().remove(alimentacion);
    }

    public Alimentacion buscarAlimentacionPorId(String id) {
        return ganadoServicio.buscarPorId(id).getAlimentaciones().stream().filter(alimentacion -> alimentacion.getAlimentacion_id().equals(id)).findFirst().get();
    }

    public Alimentacion buscarAlimentacionPorIdGanado(String id) {
        return ganadoServicio.buscarPorId(id).getAlimentaciones().stream().filter(alimentacion -> alimentacion.getGanado_id().equals(id)).findFirst().get();
    }

}
