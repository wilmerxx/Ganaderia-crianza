package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import java.util.List;

public interface ControlEnfermedadesServicio {
     void guardarControlEnfermedades(ControlNuevoDto controlNuevoDto);

     void actualizarContolEnfermedades(ControlExisteDto controlExisteDto);

     void eliminarControlEnfermedades(int id);

     List<ControlDto> listaControlEnfermedades();
     List<ControlDto> listaControlEnfermedadesPorEstado(String estado);

     ControlDto buscarControlEnfermedadesPorId(int id);
     void actualizarEstadoControlEnfermedades(int id, String estado);
}
