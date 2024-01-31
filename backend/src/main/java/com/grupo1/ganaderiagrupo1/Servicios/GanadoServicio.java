package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GanadoServicio {
    public List<GanadoDto> buscarTodos();
    public void guardar(GanadoNuevoDto o);
    public void actualizar(GanadoExisteDto o);
    public GanadoDto buscarPorId(int id);
    public void eliminar(int id);
    //filtrar por tipo de vaca
    public List<GanadoDto> buscarPorTipo(String tipo);

    public void actualizarEstado(String estado, int id);

    List<GanadoDto> gandosPorEstadosAsc(String estado);

    public List<GanadoDto> buscarPorNombre(String nombre,int page, int size);
}
