package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;

import java.util.List;

public interface MedicinaServicio {
    public void guardarMedicina(Medicina medicina);

    public void actualizarMedicina(Medicina medicina);

    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades);

    public List<Medicina> listaMedicina();
    public Medicina buscarMedicinaPorId(int id);
}
