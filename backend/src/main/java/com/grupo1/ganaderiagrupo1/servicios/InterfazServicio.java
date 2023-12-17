package com.grupo1.ganaderiagrupo1.Servicios;

import java.util.List;

public interface InterfazServicio {
    public void guardar(Object o);
    public void eliminar(Object o);
    public void actualizar(Object o);
    public Object buscarPorId(String id);
}
