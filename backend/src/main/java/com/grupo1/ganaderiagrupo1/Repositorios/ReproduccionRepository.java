package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReproduccionRepository {

    private List<Reproduccion> reproduccionesList = new ArrayList<>();

    public List<Reproduccion> buscarTodos() {
        return reproduccionesList;
    }

    public Reproduccion buscarPorId(String id) {
        return reproduccionesList.stream()
                .filter(reproduccion -> reproduccion.getReproduccion_id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void agregar(Reproduccion reproduccion) {
        // Agregar la reproducción a la lista
        reproduccionesList.add(reproduccion);

    }

    private boolean tieneGanadoTipoVaca(List<Ganado> ganados) {
        return ganados.stream().anyMatch(ganado -> "vaca".equals(ganado.getTipo()));
    }

    public void actualizar(Reproduccion reproduccion) {
       reproduccionesList.removeIf(reproduccion1 -> reproduccion1.getReproduccion_id().equals(reproduccion.getReproduccion_id()));
       reproduccionesList.add(reproduccion);
    }

    public void eliminar(String id) {
        reproduccionesList.removeIf(reproduccion -> reproduccion.getReproduccion_id().equals(id));
    }
}
