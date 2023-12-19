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
        // Verificar que haya al menos un ganado tipo vaca en la lista
        if (reproduccion.getGanados() != null && tieneGanadoTipoVaca(reproduccion.getGanados())) {
            // Agregar la reproducción a la lista
            reproduccionesList.add(reproduccion);
        } else {
            throw new IllegalArgumentException("La reproducción debe tener al menos un ganado de tipo vaca");
        }
    }

    private boolean tieneGanadoTipoVaca(List<Ganado> ganados) {
        return ganados.stream().anyMatch(ganado -> "vaca".equals(ganado.getTipo()));
    }

    public void actualizar(Reproduccion reproduccion) {
        Reproduccion reproduccionExistente = buscarPorId(reproduccion.getReproduccion_id());
        if (reproduccionExistente != null) {
            reproduccionExistente.setFecha_parto(reproduccion.getFecha_parto());
            reproduccionExistente.setEstado_salud(reproduccion.getEstado_salud());
            reproduccionExistente.setNumero_crias(reproduccion.getNumero_crias());

            // Asegúrate de no perder la lista existente de ganados
            List<Ganado> ganados = reproduccionExistente.getGanados();
            if (reproduccion.getGanados() != null) {
                ganados.clear();
                ganados.addAll(reproduccion.getGanados());
            }
        }
    }

    public void eliminar(String id) {
        reproduccionesList.removeIf(reproduccion -> reproduccion.getReproduccion_id().equals(id));
    }
}
