package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import java.util.List;

public interface AlimentacionServicio {
    public void guardarAlimentacion(AlimentacionNuevoDto alimentacion);

    public void actualizarAlimentacion(AlimentacionExisteDto alimentacion);
    public List<AlimentacionDto> listaAlimentacion();

    public void eliminarAlimentacion(Alimentacion alimentacion);

    public AlimentacionDto buscarAlimentacionPorId(int id);
}
