package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GanadoRepositorio implements InterfazRepositorio {
    Date fecha = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
    List<Ganado> ganadoList = new ArrayList<>();


    @Override
    public void guardar(Object o) {
        ganadoList.add((Ganado) o);
    }

    @Override
    public void cambioEstado(Object o, String estado) {

    }

    @Override
    public void actualizar(Object o) {

    }

    @Override
    public Object buscarPorId(String id) {
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
