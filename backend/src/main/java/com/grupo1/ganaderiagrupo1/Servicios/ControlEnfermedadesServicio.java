package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import java.util.List;

public interface ControlEnfermedadesServicio {
    public void guardarControlEnfermedades(ControlEnfermedades controlEnfermedades);

    public void actualizarContolEnfermedades(ControlEnfermedades controlEnfermedades);

    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades);

    public List<ControlEnfermedades> listaControlEnfermedades();

    public ControlEnfermedades buscarControlEnfermedadesPorId(int id);
}
