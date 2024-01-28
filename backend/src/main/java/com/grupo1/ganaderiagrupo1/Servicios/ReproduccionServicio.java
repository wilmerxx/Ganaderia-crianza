package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;

import java.util.List;

public interface ReproduccionServicio {
    public ReproduccionDto buscarPorId(int id);
    public List<ReproduccionDto> buscarTodos();
    public List<ReproduccionDto> buscarPorEstado(String estado);
    public void actualizarEstado(int id, String estado);
    public void agregarReproduccion(ReproduccionNuevoDto reproduccionNuevoDto);
    public void eliminarReproduccion(int id);

    public void actualizarReproduccion(ReproduccionExisteDto reproduccionExisteDto) ;
}
