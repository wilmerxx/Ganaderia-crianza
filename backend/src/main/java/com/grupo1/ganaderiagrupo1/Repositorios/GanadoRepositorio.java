package com.grupo1.ganaderiagrupo1.Repositorios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GanadoRepositorio {
    List<Ganado> ganadoList = new ArrayList<>();

    public void guardar(Object o) {
        ganadoList.add((Ganado) o);
    }

    public void cambioEstado(Ganado o, String estado) {
        Ganado ganado = buscarPorId(o.getGanado_id());
        ganado.setEstado(estado);

    }

    public void actualizar(Ganado o) {
        Ganado ganado = buscarPorId(o.getGanado_id());
        ganado.setEstado(o.getEstado());
        ganado.setFechaNacimiento(o.getFechaNacimiento());
        ganado.setPeso(o.getPeso());
        ganado.setRaza(o.getRaza());
        ganado.setSexo(o.getSexo());
        ganado.setTipo(o.getTipo());
        ganado.setNombre_ganado(o.getNombre_ganado());
        ganado.setCodigo(o.getCodigo());
    }

    public Ganado buscarPorId(String id) {
        for (Ganado ganado: ganadoList) {
            if (ganado.getGanado_id().equals(id)){
                return ganado;
            }
        }
        return null;
    }

    public List<Ganado> buscarTodos() {
       return ganadoList;
    }


}
