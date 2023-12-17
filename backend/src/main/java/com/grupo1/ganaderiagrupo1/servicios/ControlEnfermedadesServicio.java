package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Repositorios.ControlEnfermedadesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ControlEnfermedadesServicio {
    @Autowired
    private ControlEnfermedadesRepositorio controlEnfermedadesRepositorio;

   public List<ControlEnfermedades> buscarTodos() {
            return controlEnfermedadesRepositorio.buscarTodos();
   }

    public void cambioEstado(String id, String estado) {

    }

    public Object buscarPorId(String id) {
        return null;
    }
}
