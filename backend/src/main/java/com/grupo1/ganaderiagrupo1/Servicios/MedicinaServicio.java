package com.grupo1.ganaderiagrupo1.Servicios;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaDto;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;

import java.util.List;

public interface MedicinaServicio {
    public void guardarMedicina(MedicinaNuevoDto medicinaNuevoDto);
    public void actualizarMedicina(MedicinaExisteDto medicinaExisteDto);
    public void elimnarMedicina(int id);
    public void actualizarEstadoMedicina(int id, String estado);
    public List<MedicinaDto> listaMedicina();
    public List<MedicinaDto> listaMedicinaPorEstado(String estado);
    public MedicinaDto buscarMedicinaPorId(int id);
}
