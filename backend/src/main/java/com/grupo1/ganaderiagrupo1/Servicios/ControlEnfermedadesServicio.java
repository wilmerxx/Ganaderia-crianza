package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlEnfermedadesServicio {
    @Autowired
    private ControlEnfermedadesRepositorio controlEnfermedadesRepositorio;
    @Autowired
    private GanadoServicio ganadoServicio;


    // Control de enfermedades
    public void guardarControlEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = ganadoServicio.buscarPorId(controlEnfermedades.getGanado().getGanado_id());
        ganado.getControlEnfermedades().add(controlEnfermedades);
        controlEnfermedadesRepositorio.guardar(controlEnfermedades);
    }

    public void actualizarContolEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = ganadoServicio.buscarPorId(controlEnfermedades.getGanado().getGanado_id());
        ganado.getControlEnfermedades().removeIf(controlEnfermedades1 -> controlEnfermedades1.getControl_id().equals(controlEnfermedades.getControl_id()));
        ganado.getControlEnfermedades().add(controlEnfermedades);
        controlEnfermedadesRepositorio.actualizar(controlEnfermedades);
    }

    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades) {
        Ganado ganado = ganadoServicio.buscarPorId(controlEnfermedades.getGanado().getGanado_id());
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

}
