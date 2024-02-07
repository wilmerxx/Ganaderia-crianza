package com.grupo1.ganaderiagrupo1.Servicios.impl;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Ganado.GanadoNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ResourceNotFoundException;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GanadoServicioImpl implements com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio {

    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public List<GanadoDto> buscarTodos() {
        //buscar todos los ganados
        List<Ganado> ganados = ganadoRepositorio.todosGanados();
       if(ganados.isEmpty()){
           throw new ResourceNotFoundException("p-222","No hay ganados registrados", HttpStatus.NOT_FOUND);
       }
        //convertir a dto
        List<GanadoDto> ganadoDtos = new ArrayList<>();
        if(!ganados.isEmpty()){
            for(Ganado ganado : ganados){
                GanadoDto ganadoDto = new GanadoDto();
                if(ganado.getMadre() != null){
                    ganadoDto.setNombre_madre(ganado.getMadre().getNombre_ganado());

                }else {
                    ganadoDto.setNombre_madre("No tiene madre");
                }
                if(ganado.getPadre() != null){
                    ganadoDto.setNombre_padre(ganado.getPadre().getNombre_ganado());
                }else{
                    ganadoDto.setNombre_padre("No tiene padre");
                }
                //validar el nombre que no sea repetido
                if(ganado.getNombre_ganado().equals(ganadoDto.getNombre_ganado())){
                    throw new ResourceNotFoundException("p-222","El nombre del ganado ya existe", HttpStatus.NOT_FOUND);
                }
                ganadoDto.setGanado_id(ganado.getGanado_id());
                ganadoDto.setCodigo(ganado.getCodigo());
                ganadoDto.setNombre_ganado(ganado.getNombre_ganado());
                ganadoDto.setRaza(ganado.getRaza());
                ganadoDto.setPeso(ganado.getPeso());
                ganadoDto.setSexo(ganado.getSexo());
                ganadoDto.setTipo(ganado.getTipo());
                ganadoDto.setFechaNacimiento(ganado.getFechaNacimiento());
                ganadoDto.setEstado(ganado.getEstado());
                ganadoDto.setEdad(ganadoDto.calcularEdad(ganado.getFechaNacimiento()));
                ganadoDto.setCreado(ganado.getCreado());
                ganadoDto.setModificado(ganado.getModificado());
                ganadoDtos.add(ganadoDto);
            }
        }
        //retornar dto
        return ganadoDtos;
    }

    @Override
    public void guardar(GanadoNuevoDto ganadoNuevoDto) {
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for(Ganado ganado : ganados){
            if(ganado.getCodigo().equals(ganadoNuevoDto.getCodigo())){
                throw new ResourceNotFoundException("p-222","Ya existe un ganado con ese codigo", HttpStatus.NOT_FOUND);
            }
        }
        //convertir a modelo
        Ganado ganado = new Ganado();
        ganado.setCodigo(ganadoNuevoDto.getCodigo());
        ganado.setNombre_ganado(ganadoNuevoDto.getNombre_ganado());
        ganado.setRaza(ganadoNuevoDto.getRaza());
        ganado.setPeso(ganadoNuevoDto.getPeso());
        ganado.setSexo(ganadoNuevoDto.getSexo());
        ganado.setFechaNacimiento(ganadoNuevoDto.getFechaNacimiento());
        ganado.setEstado(ganadoNuevoDto.getEstado());
        ganado.setEdad(ganadoNuevoDto.calcularEdad(ganadoNuevoDto.getFechaNacimiento()));
        ganado.setTipo(ganadoNuevoDto.tipoGanado(ganadoNuevoDto.getFechaNacimiento(), ganadoNuevoDto.getSexo()));

        //buscar madre
        Optional<Ganado> madre = ganadoRepositorio.findById(ganadoNuevoDto.getGanado_madre_id());
        if(madre.isPresent()){
            ganado.setMadre(madre.get());
        }
        //buscar padre
        Optional<Ganado> padre = ganadoRepositorio.findById(ganadoNuevoDto.getGanado_padre_id());
        if(padre.isPresent()){
            ganado.setPadre(padre.get());
        }

        //guardar modelo
         ganadoRepositorio.save(ganado);

    }


    @Override
    public void actualizar(GanadoExisteDto o) {
        //actualizar ganado
        Optional<Ganado> ganado = ganadoRepositorio.findById(o.getGanado_id());
        if(!ganado.isPresent()){
            throw new ResourceNotFoundException("p-222","No existe un ganado con ese id", HttpStatus.NOT_FOUND);
        }
        ganado.get().setCodigo(o.getCodigo());
        ganado.get().setNombre_ganado(o.getNombre_ganado());
        ganado.get().setRaza(o.getRaza());
        ganado.get().setPeso(o.getPeso());
        ganado.get().setSexo(o.getSexo());
        ganado.get().setFechaNacimiento(o.getFechaNacimiento());
        ganado.get().setEdad(o.calcularEdad(o.getFechaNacimiento()));
        ganado.get().setTipo(o.tipoGanado(o.getFechaNacimiento(), o.getSexo()));
        //buscar madre
        Optional<Ganado> madre = ganadoRepositorio.findById(o.getGanado_madre_id());
        madre.ifPresent(value -> ganado.get().setMadre(value));
        //buscar padre
        Optional<Ganado> padre = ganadoRepositorio.findById(o.getGanado_padre_id());
        padre.ifPresent(value -> ganado.get().setPadre(value));
        //guardar modelo
        ganadoRepositorio.save(ganado.get());
    }

    @Override
    public GanadoDto buscarPorId(int id) {

        Optional<Ganado> ganado = ganadoRepositorio.findById(id);
        if(ganado.isPresent()) {
            GanadoDto ganadoDto = new GanadoDto();
            ganadoDto.setGanado_id(ganado.get().getGanado_id());
            ganadoDto.setCodigo(ganado.get().getCodigo());
            ganadoDto.setNombre_ganado(ganado.get().getNombre_ganado());
            ganadoDto.setRaza(ganado.get().getRaza());
            ganadoDto.setPeso(ganado.get().getPeso());
            ganadoDto.setSexo(ganado.get().getSexo());
            ganadoDto.setFechaNacimiento(ganado.get().getFechaNacimiento());
            ganadoDto.setEstado(ganado.get().getEstado());
            ganadoDto.setEdad(ganado.get().getEdad());
            ganadoDto.setTipo(ganado.get().getTipo());
            ganadoDto.setCreado(ganado.get().getCreado());
            ganadoDto.setModificado(ganado.get().getModificado());
            if(ganado.get().getMadre() != null){
                ganadoDto.setNombre_madre(ganado.get().getMadre().getNombre_ganado());

            }else {
                ganadoDto.setNombre_madre("No tiene madre");
            }
            if(ganado.get().getPadre() != null){
                ganadoDto.setNombre_padre(ganado.get().getPadre().getNombre_ganado());
            }else{
                ganadoDto.setNombre_padre("No tiene padre");
            }
            return ganadoDto;
        }else{
            throw new ResourceNotFoundException("p-222","No existe un ganado con ese id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void eliminar(int id) {
        //cambiar estado a inactivo
        Optional<Ganado> ganado = ganadoRepositorio.findById(id);
        if(ganado.isPresent()){
            ganado.get().setEstado("Inactivo");
            ganadoRepositorio.save(ganado.get());
        }else{
            throw new ResourceNotFoundException("p-222","No existe un ganado con ese id", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<GanadoDto> buscarPorTipo(String tipo) {
        //buscar por tipo
        List<Ganado> ganados = ganadoRepositorio.findAll();
        List<GanadoDto> ganadoDtos = new ArrayList<>();
        if(!ganados.isEmpty()){
            for(Ganado ganado : ganados){
                if(ganado.getTipo().equals(tipo)){
                    GanadoDto ganadoDto = new GanadoDto();
                    if(ganado.getMadre() != null){
                        ganadoDto.setNombre_madre(ganado.getMadre().getNombre_ganado());

                    }else {
                        ganadoDto.setNombre_madre("No tiene madre");
                    }
                    if(ganado.getPadre() != null){
                        ganadoDto.setNombre_padre(ganado.getPadre().getNombre_ganado());
                    }else{
                        ganadoDto.setNombre_padre("No tiene padre");
                    }
                    ganadoDto.setGanado_id(ganado.getGanado_id());
                    ganadoDto.setCodigo(ganado.getCodigo());
                    ganadoDto.setNombre_ganado(ganado.getNombre_ganado());
                    ganadoDto.setRaza(ganado.getRaza());
                    ganadoDto.setPeso(ganado.getPeso());
                    ganadoDto.setSexo(ganado.getSexo());
                    ganadoDto.setTipo(ganado.getTipo());
                    ganadoDto.setFechaNacimiento(ganado.getFechaNacimiento());
                    ganadoDto.setEstado(ganado.getEstado());
                    ganadoDto.setEdad(ganado.getEdad());
                    ganadoDto.setCreado(ganado.getCreado());
                    ganadoDto.setModificado(ganado.getModificado());
                    ganadoDtos.add(ganadoDto);
                }
            }
        }
        //retornar dto
        return ganadoDtos;
    }

    @Override
    public void actualizarEstado(String estado, int id) {
       //actualizar el estado del ganado
        Optional<Ganado> ganado = ganadoRepositorio.findById(id);
        if(ganado.isPresent()){
            ganado.get().setEstado(estado);
            ganadoRepositorio.save(ganado.get());
        }else{
            throw new ResourceNotFoundException("p-222","No existe un ganado con ese id", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public List<GanadoDto> gandosPorEstadosAsc(String estado){
        List<Ganado> ganados =  ganadoRepositorio.gandosPorEstadosAsc(estado);
        List<GanadoDto> ganadoDtos = new ArrayList<>();
        if(!ganados.isEmpty()){
            for(Ganado ganado : ganados){
                if(ganado.getEstado().equals(estado)){
                    GanadoDto ganadoDto = new GanadoDto();
                    if(ganado.getMadre() != null){
                        ganadoDto.setNombre_madre(ganado.getMadre().getNombre_ganado());

                    }else {
                        ganadoDto.setNombre_madre("No tiene madre");
                    }
                    if(ganado.getPadre() != null){
                        ganadoDto.setNombre_padre(ganado.getPadre().getNombre_ganado());
                    }else{
                        ganadoDto.setNombre_padre("No tiene padre");
                    }
                    ganadoDto.setGanado_id(ganado.getGanado_id());
                    ganadoDto.setCodigo(ganado.getCodigo());
                    ganadoDto.setNombre_ganado(ganado.getNombre_ganado());
                    ganadoDto.setRaza(ganado.getRaza());
                    ganadoDto.setPeso(ganado.getPeso());
                    ganadoDto.setSexo(ganado.getSexo());
                    ganadoDto.setTipo(ganado.getTipo());
                    ganadoDto.setFechaNacimiento(ganado.getFechaNacimiento());
                    ganadoDto.setEstado(ganado.getEstado());
                    ganadoDto.setEdad(ganado.getEdad());
                    ganadoDto.setCreado(ganado.getCreado());
                    ganadoDto.setModificado(ganado.getModificado());
                    ganadoDtos.add(ganadoDto);
                }else{
                    throw new ResourceNotFoundException("p-222","No existe un ganado con ese estado", HttpStatus.NOT_FOUND);
                }
            }
        }else{
            throw new ResourceNotFoundException("p-222","No existe un ganados en la lista", HttpStatus.NOT_FOUND);
        }
        //retornar dto
        return ganadoDtos;
    }

    @Override
    public List<GanadoDto> buscarPorNombre(String nombre, int page, int size) {
        Pageable pegaable = PageRequest.of(page, size, Sort.by("ganado_id").descending());
        //buscar por nombre
        Page<Ganado> ganados = ganadoRepositorio.buscarPorNombre(nombre, pegaable);
        if(ganados.isEmpty()){
            throw new ResourceNotFoundException("p-222","No existe un ganado con ese nombre", HttpStatus.NOT_FOUND);
        }
        //convertir a dto
        List<GanadoDto> ganadoDtos = new ArrayList<>();
        if(!ganados.isEmpty()){
            for(Ganado ganado : ganados){
                GanadoDto ganadoDto = new GanadoDto();
                if(ganado.getMadre() != null){
                    ganadoDto.setNombre_madre(ganado.getMadre().getNombre_ganado());

                }else {
                    ganadoDto.setNombre_madre("No tiene madre");
                }
                if(ganado.getPadre() != null){
                    ganadoDto.setNombre_padre(ganado.getPadre().getNombre_ganado());
                }else{
                    ganadoDto.setNombre_padre("No tiene padre");
                }
                ganadoDto.setGanado_id(ganado.getGanado_id());
                ganadoDto.setCodigo(ganado.getCodigo());
                ganadoDto.setNombre_ganado(ganado.getNombre_ganado());
                ganadoDto.setRaza(ganado.getRaza());
                ganadoDto.setPeso(ganado.getPeso());
                ganadoDto.setSexo(ganado.getSexo());
                ganadoDto.setTipo(ganado.getTipo());
                ganadoDto.setFechaNacimiento(ganado.getFechaNacimiento());
                ganadoDto.setEstado(ganado.getEstado());
                ganadoDto.setEdad(ganado.getEdad());
                ganadoDto.setCreado(ganado.getCreado());
                ganadoDto.setModificado(ganado.getModificado());
                ganadoDtos.add(ganadoDto);
            }
        }
        //retornar dto
        return ganadoDtos;


    }
}
