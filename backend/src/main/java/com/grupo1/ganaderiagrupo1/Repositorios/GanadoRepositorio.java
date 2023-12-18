package com.grupo1.ganaderiagrupo1.Repositorios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GanadoRepositorio {

    Date date = new Date();

    List<Ganado> ganadoList = new ArrayList<>();
    /*List<Ganado> ganadoList = new ArrayList<>(
            List.of(
                    new Ganado("1", "1", "1", "1", 345, "1", date, "1", "1"),
                    new Ganado("2", "2", "2", "2", 345, "2", date, "2", "2"),
                    new Ganado("3", "3", "3", "3", 64, "3", date, "3", "3"),
                    new Ganado("4", "4", "4", "4", 456, "4", date, "4", "4"),
                    new Ganado("5", "5", "5", "5", 456, "5", date, "5", "5"),
                    new Ganado("6", "6", "6", "6", 456, "6", date, "6", "6"),
                    new Ganado("7", "7", "7", "7", 456, "7", date, "7", "7"),
                    new Ganado("8", "8", "8", "8", 46, "8", date, "8", "8"),
                    new Ganado("9", "9", "9", "9", 456, "9", date, "9", "9"),
                    new Ganado("10", "10", "10", "10", 456, "10", date, "10", "10"),
                    new Ganado("11", "11", "11", "11", 456, "11", date, "11", "11"),
                    new Ganado("12", "12", "12", "12", 456, "12", date, "12", "12"),
                    new Ganado("13", "13", "13", "13", 456, "13", date, "13", "13"),
                    new Ganado("14", "14", "14", "14", 45, "14", date, "14", "14"),
                    new Ganado("15", "15", "15", "15", 2, "15", date, "15", "15"),
                    new Ganado("16", "16", "16", "16", 5, "16", date, "16", "16")
            )
    );*/

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
