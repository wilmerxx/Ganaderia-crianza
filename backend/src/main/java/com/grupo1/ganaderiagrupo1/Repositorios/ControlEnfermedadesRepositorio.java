package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ControlEnfermedadesRepositorio {
    Date date = new Date();

    List<ControlEnfermedades> controlEnfermedadesList = new ArrayList<>();

  /*List<ControlEnfermedades> controlEnfermedadesList = new ArrayList<>(
          List.of(
                  new ControlEnfermedades("1","Garrapata", 345.43, date, "100", "TM232", "Activo"),
                  new ControlEnfermedades("2","Garrapata", 345.43, date, "100", "TM222", "Activo"),
                  new ControlEnfermedades("3","Garrapata", 345.43, date, "100", "TM272", "Inactivo"),
                  new ControlEnfermedades("4","Garrapata", 345.43, date, "100", "TM242", "Activo"),
                  new ControlEnfermedades("5","Garrapata", 345.43, date, "100", "TM212", "Inactivo"),
                  new ControlEnfermedades("6","Garrapata", 345.43, date, "100", "TM292", "Activo"),
                  new ControlEnfermedades("7","Garrapata", 345.43, date, "100", "TM672", "Activo")
          )
  );*/

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
        ControlEnfermedades controlEnfermedades = buscarPorId(o.getControl_id());
        controlEnfermedades.setEstado(o.getEstado());
        controlEnfermedades.setFechaControl(o.getFechaControl());
        controlEnfermedades.setTipo_control(o.getTipo_control());
        controlEnfermedades.setPesoActual(o.getPesoActual());
        controlEnfermedades.setObservaciones(o.getObservaciones());
    }

    public void eliminar(ControlEnfermedades o) {
        controlEnfermedadesList.remove(o);
    }

}
