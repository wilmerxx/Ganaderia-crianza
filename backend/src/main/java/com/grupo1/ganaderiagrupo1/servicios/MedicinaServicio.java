package com.grupo1.ganaderiagrupo1.Servicios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Repositorios.MedicinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinaServicio {
    @Autowired
    private MedicinaRepositorio medicinaRepositorio;

    @Autowired
    private GanadoServicio ganadoServicio;

    public void guardarMedicina(Medicina medicina) {
        Ganado ganado = ganadoServicio.buscarPorId(medicina.getGanado_id());
        ganado.getMedicinas().add(medicina);
        medicinaRepositorio.guardar(medicina);
    }

    public void actualizarMedicina(Medicina medicina) {
        Ganado ganado = ganadoServicio.buscarPorId(medicina.getGanado_id());
        for (Medicina medicinaEncontrada: ganado.getMedicinas()) {
            if (medicinaEncontrada.getMedicina_id().equals(medicina.getMedicina_id())){
                medicinaEncontrada.setSintomas(medicina.getSintomas());
                medicinaEncontrada.setDiagnostico(medicina.getDiagnostico());
                medicinaEncontrada.setTratamiento(medicina.getTratamiento());
                medicinaEncontrada.setFecha_vacuna(medicina.getFecha_vacuna());

            }
        }
        medicinaRepositorio.actualizar(medicina);
    }

    public void eliminarControlEnfermedades(ControlEnfermedades controlEnfermedades) {

    }

    public List<Medicina> listaMedicina() {
        return medicinaRepositorio.buscarTodos();
    }
    public Medicina buscarMedicinaPorId(String id) {
        return medicinaRepositorio.buscarPorId(id);
    }
}
