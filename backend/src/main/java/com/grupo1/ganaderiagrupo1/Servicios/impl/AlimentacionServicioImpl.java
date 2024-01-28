package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Alimentacion.AlimentacionNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.AlimentacionRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlimentacionServicioImpl implements com.grupo1.ganaderiagrupo1.Servicios.AlimentacionServicio {
    @Autowired
    private GanadoRepositorio ganadoRepositorio;
    @Autowired
    private AlimentacionRepositorio alimentacionRepositorio;


    @Override
    public void guardarAlimentacion(AlimentacionNuevoDto alimentacionNueva) {
        // validar que el ganado exista
        Optional<Ganado> ganadoBuscado = ganadoRepositorio.findById(alimentacionNueva.getGanado_id());
        if (ganadoBuscado.isEmpty()) {
            throw new RuntimeException("El ganado no existe");
        }

        Ganado ganado = ganadoBuscado.get();
        Alimentacion alimentacion = new Alimentacion();
        alimentacion.setNombre_suplemento(alimentacionNueva.getNombre_suplemento());
        alimentacion.setFecha_alimentacion(alimentacionNueva.getFecha_alimentacion());
        alimentacion.setEstado(alimentacionNueva.getEstado());
        alimentacion.setGanado(ganado);
        alimentacion.setCantidad_suplemento(alimentacionNueva.getCantidad_suplemento());
        alimentacionRepositorio.save(alimentacion);
    }

    @Override
    public void actualizarAlimentacion(AlimentacionExisteDto alimentacion) {
        // validar que el ganado exista
        Optional<Ganado> ganadoBuscado = ganadoRepositorio.findById(alimentacion.getGanado_id());
        if (ganadoBuscado.isEmpty()) {
            throw new RuntimeException("El ganado no existe");
        }
        //validar que la alimentacion exista
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(alimentacion.getAlimentacion_id());
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        Alimentacion alimentacionActualizada = alimentacionBuscada.get();
        alimentacionActualizada.setNombre_suplemento(alimentacion.getNombre_suplemento());
        alimentacionActualizada.setFecha_alimentacion(alimentacion.getFecha_alimentacion());
        alimentacionActualizada.setEstado(alimentacion.getEstado());
        alimentacionActualizada.setGanado(ganadoBuscado.get());
        alimentacionActualizada.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
        alimentacionRepositorio.save(alimentacionActualizada);
      
    }

    @Override
    public List<AlimentacionDto> listaAlimentacion() {
        if (alimentacionRepositorio.findAll().isEmpty()) {
            throw new RuntimeException("No hay alimentacion");
        }
        List<AlimentacionDto> alimentacionDto = new ArrayList<>();
        List<Ganado> ganado = ganadoRepositorio.findAll();
        for (Alimentacion alimentacion : alimentacionRepositorio.todosAlimentacion()) {
           for (Ganado ganado1 : ganado) {
               if (alimentacion.getGanado().getGanado_id() == ganado1.getGanado_id()) {
                   AlimentacionDto alimentacionDto1 = new AlimentacionDto();
                   alimentacionDto1.setAlimentacion_id(alimentacion.getAlimentacion_id());
                   alimentacionDto1.setNombre_suplemento(alimentacion.getNombre_suplemento());
                   alimentacionDto1.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
                   alimentacionDto1.setFecha_alimentacion(alimentacion.getFecha_alimentacion());
                   alimentacionDto1.setEstado(alimentacion.getEstado());
                   alimentacionDto1.setCodigo(ganado1.getCodigo());
                   alimentacionDto1.setNombre_ganado(ganado1.getNombre_ganado());
                   alimentacionDto1.setCreado(alimentacion.getCreado());
                   alimentacionDto1.setModificado(alimentacion.getModificado());
                   alimentacionDto.add(alimentacionDto1);
               }
           }
        }
        return alimentacionDto;
    }

    @Override
    public List<AlimentacionDto> listaAlimentacionPorEstado(String estado) {
        if (alimentacionRepositorio.findAll().isEmpty()) {
            throw new RuntimeException("No hay alimentacion");
        }
        List<AlimentacionDto> alimentacionDto = new ArrayList<>();
        List<Alimentacion> alimentacions = alimentacionRepositorio.alimentacionPorEstadosAsc(estado);
        List<Ganado> ganado = ganadoRepositorio.findAll();
        for (Alimentacion alimentacion : alimentacions) {
            for (Ganado ganado1 : ganado) {
                if (alimentacion.getGanado().getGanado_id() == ganado1.getGanado_id()) {
                    AlimentacionDto alimentacionDto1 = new AlimentacionDto();
                    alimentacionDto1.setAlimentacion_id(alimentacion.getAlimentacion_id());
                    alimentacionDto1.setNombre_suplemento(alimentacion.getNombre_suplemento());
                    alimentacionDto1.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
                    alimentacionDto1.setFecha_alimentacion(alimentacion.getFecha_alimentacion());
                    alimentacionDto1.setEstado(alimentacion.getEstado());
                    alimentacionDto1.setCodigo(ganado1.getCodigo());
                    alimentacionDto1.setNombre_ganado(ganado1.getNombre_ganado());
                    alimentacionDto1.setCreado(alimentacion.getCreado());
                    alimentacionDto1.setModificado(alimentacion.getModificado());
                    alimentacionDto.add(alimentacionDto1);
                }
            }
        }


        return alimentacionDto;
    }

    @Override
    public void eliminarAlimentacion(int id) {
        //validar que la alimentacion exista
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(id);
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        //actualizar el estado de la alimentacion
        Alimentacion alimentacionEliminada = alimentacionBuscada.get();
        alimentacionEliminada.setEstado("Inactivo");
    }

    @Override
    public AlimentacionDto buscarAlimentacionPorId(int id) {
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(id);
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        Alimentacion alimentacion = alimentacionBuscada.get();
        AlimentacionDto alimentacionDto = new AlimentacionDto();
        alimentacionDto.setAlimentacion_id(alimentacion.getAlimentacion_id());
        alimentacionDto.setNombre_suplemento(alimentacion.getNombre_suplemento());
        alimentacionDto.setCantidad_suplemento(alimentacion.getCantidad_suplemento());
        alimentacionDto.setFecha_alimentacion(alimentacion.getFecha_alimentacion());
        alimentacionDto.setEstado(alimentacion.getEstado());
        alimentacionDto.setCodigo(alimentacion.getGanado().getCodigo());
        alimentacionDto.setCreado(alimentacion.getCreado());
        alimentacionDto.setModificado(alimentacion.getModificado());
        alimentacionDto.setNombre_ganado(alimentacion.getGanado().getNombre_ganado());
        return alimentacionDto;
    }

    @Override
    public void actualizarEstadoAlimentacion(int id, String estado) {
        Optional<Alimentacion> alimentacionBuscada = alimentacionRepositorio.findById(id);
        if (alimentacionBuscada.isEmpty()) {
            throw new RuntimeException("La alimentacion no existe");
        }
        Alimentacion alimentacion = alimentacionBuscada.get();
        alimentacion.setEstado(estado);
    }
}
