package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

<<<<<<< HEAD
=======
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
>>>>>>> Desarrollo
import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;

import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.MedicinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GanadoServicio {

    @Autowired
    private GanadoRepositorio ganadoRepositorio;
    @Autowired
    private ControlEnfermedadesRepositorio controlEnfermedadesRepositorio;
    @Autowired
    private MedicinaRepositorio medicinaRepositorio;

    public List<Ganado> buscarTodos() {
        return ganadoRepositorio.buscarTodos();
    }
    public void guardar(Object o) {
        ganadoRepositorio.guardar(o);
    }

    public void eliminar(Object o) {

    }

    public void actualizar(Ganado o) {
        ganadoRepositorio.actualizar(o);

    }

    public Ganado buscarPorId(String id) {
        return ganadoRepositorio.buscarPorId(id);
    }


    // Control de enfermedades
    public void guardarControlEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = buscarPorId(controlEnfermedades.getGanado_id());
        ganado.getControlEnfermedades().add(controlEnfermedades);
        controlEnfermedadesRepositorio.guardar(controlEnfermedades);
    }

    public void actualizarContolEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = buscarPorId(controlEnfermedades.getGanado_id());
        for (ControlEnfermedades controlEnfermedades1: ganado.getControlEnfermedades()) {
            if (controlEnfermedades1.getControl_id().equals(controlEnfermedades.getControl_id())){
                controlEnfermedades1.setEstado(controlEnfermedades.getEstado());
                controlEnfermedades1.setFechaControl(controlEnfermedades.getFechaControl());
                controlEnfermedades1.setTipo_control(controlEnfermedades.getTipo_control());
                controlEnfermedades1.setPesoActual(controlEnfermedades.getPesoActual());
                controlEnfermedades1.setObservaciones(controlEnfermedades.getObservaciones());
            }
        }
        controlEnfermedadesRepositorio.actualizar(controlEnfermedades);
    }

    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = buscarPorId(controlEnfermedades.getGanado_id());
        ControlEnfermedades controlEnfermedades1 = controlEnfermedadesRepositorio.buscarPorId(controlEnfermedades.getControl_id());
        ganado.getControlEnfermedades().remove(controlEnfermedades);
        controlEnfermedadesRepositorio.eliminar(controlEnfermedades1);
    }

    public List<ControlEnfermedades> listaControlEnfermedades() {
        return controlEnfermedadesRepositorio.buscarTodos();
    }

    public ControlEnfermedades buscarControlEnfermedadesPorId(String id) {
        return controlEnfermedadesRepositorio.buscarPorId(id);
    }

    // Control de vacunas


}
