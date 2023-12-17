package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ControlEnfermedadesRepositorio {

  List<ControlEnfermedades> controlEnfermedadesList = new ArrayList<>();

  public List<ControlEnfermedades> buscarTodos() {
    return controlEnfermedadesList;
  }

    public ControlEnfermedades buscarPorId(String id) {
        for (ControlEnfermedades controlEnfermedades: controlEnfermedadesList) {
            if (controlEnfermedades.getControl_id().equals(id)){
                return controlEnfermedades;
            }
        }
        return null;
    }

    public void guardar(ControlEnfermedades o) {
        controlEnfermedadesList.add(o);
    }

    public void cambioEstado( ControlEnfermedades o) {
        ControlEnfermedades controlEnfermedades = buscarPorId(o.getControl_id());
        controlEnfermedades.setEstado(o.getEstado());
    }

    public void actualizar(ControlEnfermedades o) {
    controlEnfermedadesList.add(o);
    }

}
