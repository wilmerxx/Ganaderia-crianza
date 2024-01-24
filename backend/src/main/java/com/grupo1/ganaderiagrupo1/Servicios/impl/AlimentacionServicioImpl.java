package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.AlimentacionRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlimentacionServicioImpl implements com.grupo1.ganaderiagrupo1.Servicios.AlimentacionServicio {
    @Autowired
    private GanadoRepositorio ganadoRepositorio;
    @Autowired
    private AlimentacionRepositorio alimentacionRepositorio;


    @Override
    public void guardarAlimentacion(Alimentacion alimentacion) {
        // validar que el ganado exista
        Optional<Ganado> ganadoBuscado = ganadoRepositorio.findById(alimentacion.getGanado().getGanado_id());
        if (ganadoBuscado.isEmpty()) {
            throw new RuntimeException("El ganado no existe");
        }
        ganadoRepositorio.save(alimentacion.getGanado());
    }

    @Override
    public void actualizarAlimentacion(Alimentacion alimentacion) {
        // validar que el ganado exista
        Optional<Ganado> ganadoBuscado = ganadoRepositorio.findById(alimentacion.getGanado().getGanado_id());
        if (ganadoBuscado.isEmpty()) {
            throw new RuntimeException("El ganado no existe");
        }
        //validar que la alimentacion exista
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(alimentacion.getAlimentacion_id());
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        Alimentacion alimentacionActualizada = alimentacionBuscada.get();
        alimentacionActualizada.setNombre_suplemento(alimentacion.getNombre_suplemento());
        alimentacionActualizada.setFecha_alimentacion(alimentacion.getFecha_alimentacion());
        alimentacionActualizada.setEstado(alimentacion.getEstado());
        alimentacionActualizada.setCreado(alimentacion.getCreado());
        alimentacionActualizada.setModificado(alimentacion.getModificado());
        alimentacionActualizada.setGanado(alimentacion.getGanado());
        alimentacionActualizada.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
        alimentacionRepositorio.save(alimentacionActualizada);
      
    }

    @Override
    public List<Alimentacion> listaAlimentacion() {
        if (alimentacionRepositorio.findAll().isEmpty()) {
            throw new RuntimeException("No hay alimentacion");
        }
        return alimentacionRepositorio.findAll();
    }

    @Override
    public void eliminarAlimentacion(Alimentacion alimentacion) {
        //validar que la alimentacion exista
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(alimentacion.getAlimentacion_id());
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        //actualizar el estado de la alimentacion
        Alimentacion alimentacionEliminada = alimentacionBuscada.get();
        alimentacionEliminada.setEstado("Eliminado");
    }

    @Override
    public Alimentacion buscarAlimentacionPorId(int id) {
        return null;
    }

    @Override
    public Alimentacion buscarAlimentacionPorIdGanado(int id) {
        return null;
    }
}
