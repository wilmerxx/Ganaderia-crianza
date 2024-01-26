package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;

import java.util.List;

public interface ReproduccionServicio {
    public void agregarReproduccion(Reproduccion reproduccion);
    public void eliminarReproduccion(int id);

    public void actualizarReproduccion(Reproduccion o) ;
    public Reproduccion buscarPorId(int id);
    public List<Reproduccion> buscarTodos();
}
