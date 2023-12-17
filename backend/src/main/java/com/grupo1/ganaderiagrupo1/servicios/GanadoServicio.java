package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GanadoServicio implements InterfazServicio {

    @Autowired
    private GanadoRepositorio ganadoRepositorio;

    public List<?> buscarTodos() {
        return ganadoRepositorio.buscarTodos();
    }
    @Override
    public void guardar(Object o) {
        ganadoRepositorio.guardar(o);
    }

    @Override
    public void eliminar(Object o) {

    }

    @Override
    public void actualizar(Object o) {

    }

    @Override
    public Object buscarPorId(String id) {
        return ganadoRepositorio.buscarPorId(id);
    }

}
