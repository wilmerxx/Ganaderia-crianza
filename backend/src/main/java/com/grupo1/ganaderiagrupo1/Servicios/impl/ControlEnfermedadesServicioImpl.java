package com.grupo1.ganaderiagrupo1.Servicios.impl;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.ControlEnfermedades.ControlNuevoDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoDto;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.ControlEnfermedadesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ControlEnfermedadesServicioImpl implements ControlEnfermedadesServicio {
    @Autowired
    private ControlEnfermedadesRepositorio controlEnfermedadesRepositorio;
    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public void guardarControlEnfermedades(ControlNuevoDto controlNuevoDto) {
        Optional<Ganado> ganado = ganadoRepositorio.findById(controlNuevoDto.getGanadoId());
        if (ganado.isPresent()) {
            ControlEnfermedades controlEnfermedades = new ControlEnfermedades();
            controlEnfermedades.setTipo_control(controlNuevoDto.getTipo_control());
            controlEnfermedades.setPesoActual(controlNuevoDto.getPesoActual());
            controlEnfermedades.setFechaControl(controlNuevoDto.getFechaControl());
            controlEnfermedades.setObservaciones(controlNuevoDto.getObservaciones());
            controlEnfermedades.setGanado(ganado.get());
            controlEnfermedades.setEstado(controlNuevoDto.getEstado());
            controlEnfermedadesRepositorio.save(controlEnfermedades);

        } else {
            throw new RuntimeException("No existe el ganado con el id: " + controlNuevoDto.getGanadoId());
        }

    }

    @Override
    public void actualizarContolEnfermedades(ControlExisteDto controlExisteDto) {

    }

    @Override
    public void eliminarControlEnfermedades(int id) {

    }

    @Override
    public List<ControlDto> listaControlEnfermedades() {
        List<ControlEnfermedades> controlEnfermedades = controlEnfermedadesRepositorio.listaControlEnfermedades();
        List<Ganado> ganados = ganadoRepositorio.findAll();
        if (controlEnfermedades.isEmpty()) {
            throw new RuntimeException("No hay controles de enfermedades registrados");
        }
        List<ControlDto> controlDtos = new ArrayList<>();
        for (ControlEnfermedades controlEnfermedades1: controlEnfermedades){
            for(Ganado ganado: ganados){
                if (controlEnfermedades1.getGanado().getGanado_id() == ganado.getGanado_id()){
                    ControlDto controlDto = new ControlDto();
                    controlDto.setControl_id(controlEnfermedades1.getControl_id());
                    controlDto.setTipo_control(controlEnfermedades1.getTipo_control());
                    controlDto.setPesoActual(controlEnfermedades1.getPesoActual());
                    controlDto.setFechaControl(controlEnfermedades1.getFechaControl());
                    controlDto.setObservaciones(controlEnfermedades1.getObservaciones());
                    controlDto.setCodigoGanado(ganado.getCodigo());
                    controlDto.setNombreGanado(ganado.getNombre_ganado());
                    controlDto.setEstado(controlEnfermedades1.getEstado());
                    controlDto.setCreado(controlEnfermedades1.getCreado());
                    controlDto.setModificado(controlEnfermedades1.getModificado());
                    controlDtos.add(controlDto);
                }
            }

        }
        return controlDtos;

    }

    @Override
    public List<ControlDto> listaControlEnfermedadesPorEstado(String estado) {
        return null;
    }

    @Override
    public ControlDto buscarControlEnfermedadesPorId(int id) {
        return null;
    }

    @Override
    public void actualizarEstadoControlEnfermedades(int id, String estado) {

    }
}
