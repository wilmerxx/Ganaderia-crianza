package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Ganado;

import java.util.List;

public interface InterfazRepositorio {
    public void guardar(Object o);
    public void cambioEstado(Object o, String estado);
    public void actualizar(Object o);
    public Object buscarPorId(String id);
}
