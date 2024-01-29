package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Reproduccion.ReproduccionNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.ReproduccionRepository;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;
import com.grupo1.ganaderiagrupo1.Servicios.ReproduccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReproduccionServiceImpl implements ReproduccionServicio {

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public ReproduccionDto buscarPorId(int id) {
        Optional<Reproduccion> reproduccion = reproduccionRepository.findById(id);
        if (reproduccion.isPresent()) {
            Optional<Ganado> ganado = ganadoRepositorio.findById(reproduccion.get().getGanado().getGanado_id());
            ReproduccionDto reproduccionDto = new ReproduccionDto();
            reproduccionDto.setReproduccion_id(reproduccion.get().getReproduccion_id());
            reproduccionDto.setFecha_parto(reproduccion.get().getFecha_parto());
            reproduccionDto.setEstado_parto(reproduccion.get().getEstado_parto());
            reproduccionDto.setObservaciones(reproduccion.get().getObservaciones());
            reproduccionDto.setNumero_crias(reproduccion.get().getNumero_crias());
            reproduccionDto.setEstado(reproduccion.get().getEstado());
            reproduccionDto.setCodigoGanado(ganado.get().getCodigo());
            reproduccionDto.setNombreGanado(ganado.get().getNombre_ganado());
            reproduccionDto.setCreado(reproduccion.get().getCreado());
            reproduccionDto.setModificado(reproduccion.get().getModificado());
            return reproduccionDto;
        }else{
            throw new RuntimeException("No se encontro la reproduccion con el id: " + id);
        }
    }

    @Override
    public List<ReproduccionDto> buscarTodos() {
        List<Reproduccion> listaReproducciones = reproduccionRepository.listaReproducciones();
        if (listaReproducciones.isEmpty()) {
            throw new RuntimeException("No hay reproducciones");
        }
        List<ReproduccionDto> listaReproduccionesDto = new ArrayList<>();
        List<Ganado> listaGanado = ganadoRepositorio.findAll();
        for (Reproduccion reproduccion : listaReproducciones) {
            for (Ganado ganado : listaGanado) {
                if (reproduccion.getGanado().getGanado_id() == ganado.getGanado_id()) {
                    ReproduccionDto reproduccionDto = new ReproduccionDto();
                    reproduccionDto.setReproduccion_id(reproduccion.getReproduccion_id());
                    reproduccionDto.setFecha_parto(reproduccion.getFecha_parto());
                    reproduccionDto.setEstado_parto(reproduccion.getEstado_parto());
                    reproduccionDto.setObservaciones(reproduccion.getObservaciones());
                    reproduccionDto.setNumero_crias(reproduccion.getNumero_crias());
                    reproduccionDto.setEstado(reproduccion.getEstado());
                    reproduccionDto.setCodigoGanado(ganado.getCodigo());
                    reproduccionDto.setNombreGanado(ganado.getNombre_ganado());
                    reproduccionDto.setCreado(reproduccion.getCreado());
                    reproduccionDto.setModificado(reproduccion.getModificado());
                    listaReproduccionesDto.add(reproduccionDto);
                }
            }
        }
        return listaReproduccionesDto;
    }

    @Override
    public List<ReproduccionDto> buscarPorEstado(String estado) {
        List<Reproduccion> listaReproducciones = reproduccionRepository.listaReproduccionesPorEstado(estado);
        if (listaReproducciones.isEmpty()) {
            throw new RuntimeException("No hay reproducciones con el estado: " + estado);
        }
        List<ReproduccionDto> listaReproduccionesDto = new ArrayList<>();
        List<Ganado> listaGanado = ganadoRepositorio.findAll();
        for (Reproduccion reproduccion : listaReproducciones) {
            for (Ganado ganado : listaGanado) {
                if (reproduccion.getGanado().getGanado_id() == ganado.getGanado_id()) {
                    ReproduccionDto reproduccionDto = new ReproduccionDto();
                    reproduccionDto.setReproduccion_id(reproduccion.getReproduccion_id());
                    reproduccionDto.setFecha_parto(reproduccion.getFecha_parto());
                    reproduccionDto.setEstado_parto(reproduccion.getEstado_parto());
                    reproduccionDto.setObservaciones(reproduccion.getObservaciones());
                    reproduccionDto.setNumero_crias(reproduccion.getNumero_crias());
                    reproduccionDto.setEstado(reproduccion.getEstado());
                    reproduccionDto.setCodigoGanado(ganado.getCodigo());
                    reproduccionDto.setNombreGanado(ganado.getNombre_ganado());
                    reproduccionDto.setCreado(reproduccion.getCreado());
                    reproduccionDto.setModificado(reproduccion.getModificado());
                    listaReproduccionesDto.add(reproduccionDto);
                }
            }
        }
        return listaReproduccionesDto;
    }

    @Override
    public void actualizarEstado(int id, String estado) {
        Optional<Reproduccion> reproduccion = reproduccionRepository.findById(id);
        if (reproduccion.isPresent()) {
            reproduccion.get().setEstado(estado);
            reproduccionRepository.save(reproduccion.get());
        } else {
            throw new RuntimeException("No se encontro la reproduccion con el id: " + id);
        }
    }

    @Override
    public void agregarReproduccion(ReproduccionNuevoDto reproduccionNuevoDto) {
      Optional<Ganado> ganado = ganadoRepositorio.findById(reproduccionNuevoDto.getGanado_id());
      if (ganado.isPresent()) {
            Reproduccion reproduccion = new Reproduccion();
            reproduccion.setFecha_parto(reproduccionNuevoDto.getFecha_parto());
            reproduccion.setEstado_parto(reproduccionNuevoDto.getEstado_parto());
            reproduccion.setObservaciones(reproduccionNuevoDto.getObservaciones());
            reproduccion.setNumero_crias(reproduccionNuevoDto.getNumero_crias());
            reproduccion.setEstado(reproduccionNuevoDto.getEstado());
            reproduccion.setGanado(ganado.get());
            reproduccionRepository.save(reproduccion);
      } else {
            throw new RuntimeException("No se encontro el ganado con el id: " + reproduccionNuevoDto.getGanado_id());
      }
    }

    @Override
    public void eliminarReproduccion(int id) {
        Optional<Reproduccion> reproduccion = reproduccionRepository.findById(id);
        if (reproduccion.isPresent()) {
            reproduccion.get().setEstado("Inactivo");
            reproduccionRepository.save(reproduccion.get());
        } else {
            throw new RuntimeException("No se encontro la reproduccion con el id: " + id);
        }

    }

    @Override
    public void actualizarReproduccion(ReproduccionExisteDto reproduccionExisteDto) {
        Optional<Reproduccion> reproduccion = reproduccionRepository.findById(reproduccionExisteDto.getReproduccion_id());
        if (reproduccion.isPresent()) {
            Optional<Ganado> ganado = ganadoRepositorio.findById(reproduccionExisteDto.getGanado_id());
            reproduccion.get().setFecha_parto(reproduccionExisteDto.getFecha_parto());
            reproduccion.get().setEstado_parto(reproduccionExisteDto.getEstado_parto());
            reproduccion.get().setObservaciones(reproduccionExisteDto.getObservaciones());
            reproduccion.get().setNumero_crias(reproduccionExisteDto.getNumero_crias());
            reproduccion.get().setEstado(reproduccionExisteDto.getEstado());
            reproduccion.get().setGanado(ganado.get());
            reproduccionRepository.save(reproduccion.get());
        } else {
            throw new RuntimeException("No se encontro la reproduccion con el id: " + reproduccionExisteDto.getReproduccion_id());
        }
    }
}
