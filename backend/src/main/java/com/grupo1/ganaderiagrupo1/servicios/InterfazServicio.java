package com.grupo1.ganaderiagrupo1.Servicios;

import java.util.List;

public interface InterfazServicio {
    public void guardar(Object o, String id);
    public void cambioEstado(String id, String estado);
    public void actualizar(Object o, String id);
    public Object buscarPorId(String id);
}
