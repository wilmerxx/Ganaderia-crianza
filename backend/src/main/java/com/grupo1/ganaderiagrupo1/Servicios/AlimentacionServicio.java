package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import java.util.List;

public interface AlimentacionServicio {
    public void guardarAlimentacion(Alimentacion alimentacion);

    public void actualizarAlimentacion(Alimentacion alimentacion);
    public List<Alimentacion> listaAlimentacion();

    public void eliminarAlimentacion(Alimentacion alimentacion);

    public Alimentacion buscarAlimentacionPorId(int id);

    public Alimentacion buscarAlimentacionPorIdGanado(int id);
}
