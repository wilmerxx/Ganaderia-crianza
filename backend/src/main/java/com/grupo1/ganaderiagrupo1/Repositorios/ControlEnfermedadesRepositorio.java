package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class ControlEnfermedadesRepositorio  implements InterfazRepositorio{

    private final List<ControlEnfermedades> controlEnfermedadesList = Ganado.getControlEnfermedades();

    @Override
    public void guardar(Object o) {
        controlEnfermedadesList.add((ControlEnfermedades) o);
    }

    @Override
    public void cambioEstado(Object o, String estado) {
        // los tipos de estados son: Sospechoso, Confirmado, en tratamiento, no resuelto, resuelto, fallido
        ((ControlEnfermedades) o).setEstado(estado);
    }

    @Override
    public void actualizar(Object o) {
        //actualizar el control de enfermedades
        for (ControlEnfermedades controlEnfermedades: controlEnfermedadesList) {
            if (Objects.equals(controlEnfermedades.getControl_id(), ((ControlEnfermedades) o).getControl_id())){
                controlEnfermedadesList.remove(controlEnfermedades);
                controlEnfermedadesList.add((ControlEnfermedades) o);
            }
        }
    }

    @Override
    public Object buscarPorId(String id) {
        for (ControlEnfermedades controlEnfermedades: controlEnfermedadesList) {
            if (Objects.equals(controlEnfermedades.getControl_id(), id)){
                return controlEnfermedades;
            }
        }
        return null;
    }


    public List<ControlEnfermedades> buscarTodos() {
        //buscar todos los controles de enfermedades
        return controlEnfermedadesList;
    }
}
