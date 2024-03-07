package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionNuevoDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionTotalConsumoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;

import java.util.List;

public interface AlimentacionServicio {
    public void guardarAlimentacion(AlimentacionNuevoDto alimentacion);

    public void actualizarAlimentacion(AlimentacionExisteDto alimentacion);
    public List<AlimentacionDto> listaAlimentacion();
    public List<AlimentacionDto> listaAlimentacionPorEstado(String estado);

    public void eliminarAlimentacion(int id);

    public AlimentacionDto buscarAlimentacionPorId(int id);

    public void actualizarEstadoAlimentacion(int id, String estado);

    public List<AlimentacionTotalConsumoDto> listaAlimentacionTotalCantidad();

    public List<AlimentacionDto> listaAlimentacionRabbitmq();
}
