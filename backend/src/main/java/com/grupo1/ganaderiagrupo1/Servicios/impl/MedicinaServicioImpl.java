package com.grupo1.ganaderiagrupo1.Servicios.impl;

import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaDto;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Medicina.MedicinaNuevoDto;
import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import com.grupo1.ganaderiagrupo1.Repositorios.GanadoRepositorio;
import com.grupo1.ganaderiagrupo1.Repositorios.MedicinaRepositorio;
import com.grupo1.ganaderiagrupo1.Servicios.MedicinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicinaServicioImpl implements MedicinaServicio {
    @Autowired
    private MedicinaRepositorio medicinaRepositorio;

    @Autowired
    private GanadoRepositorio ganadoRepositorio;


    @Override
    public void guardarMedicina(MedicinaNuevoDto medicinaNuevoDto) {
        Optional<Ganado> ganado = ganadoRepositorio.findById(medicinaNuevoDto.getGanado_id());
        if(ganado.isPresent()){
            Medicina medicina = new Medicina();
            medicina.setSintomas(medicinaNuevoDto.getSintomas());
            medicina.setDiagnostico(medicinaNuevoDto.getDiagnostico());
            medicina.setTratamiento(medicinaNuevoDto.getTratamiento());
            medicina.setFecha_vacuna(medicinaNuevoDto.getFecha_vacuna());
            medicina.setEstado(medicinaNuevoDto.getEstado());
            medicina.setGanado(ganado.get());
            medicinaRepositorio.save(medicina);
        }else{
            throw new RuntimeException("No se encontro el ganado con el id: " + medicinaNuevoDto.getGanado_id());
        }

    }

    @Override
    public void actualizarMedicina(MedicinaExisteDto medicinaExisteDto) {
        Optional<Medicina> medicina = medicinaRepositorio.findById(medicinaExisteDto.getMedicina_id());
        if(medicina.isPresent()){
            Optional<Ganado> ganado = ganadoRepositorio.findById(medicinaExisteDto.getGanado_id());
            medicina.get().setSintomas(medicinaExisteDto.getSintomas());
            medicina.get().setDiagnostico(medicinaExisteDto.getDiagnostico());
            medicina.get().setTratamiento(medicinaExisteDto.getTratamiento());
            medicina.get().setFecha_vacuna(medicinaExisteDto.getFecha_vacuna());
            medicina.get().setEstado(medicinaExisteDto.getEstado());
            medicina.get().setGanado(ganado.get());
            medicinaRepositorio.save(medicina.get());
        }else{
            throw new RuntimeException("No se encontro la medicina con el id: " + medicinaExisteDto.getMedicina_id());
        }
    }

    @Override
    public void elimnarMedicina(int id) {
        Optional<Medicina> medicina = medicinaRepositorio.findById(id);
        if(medicina.isPresent()){
            medicina.get().setEstado("Inactivo");
            medicinaRepositorio.save(medicina.get());
        }else{
            throw new RuntimeException("No se encontro la medicina con el id: " + id);
        }
    }

    @Override
    public void actualizarEstadoMedicina(int id, String estado) {
        Optional<Medicina> medicina = medicinaRepositorio.findById(id);
        if(medicina.isPresent()){
            medicina.get().setEstado(estado);
            medicinaRepositorio.save(medicina.get());
        }else{
            throw new RuntimeException("No se encontro la medicina con el id: " + id);
        }

    }

    @Override
    public List<MedicinaDto> listaMedicina() {
        if(medicinaRepositorio.listaMedicinas().isEmpty()){
            throw new RuntimeException("No se encontraron medicinas");
        }
        List<MedicinaDto> medicinasDto = new ArrayList<>();
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for (Medicina medicina: medicinaRepositorio.listaMedicinas()) {
            for (Ganado ganado: ganados) {
                if(medicina.getGanado().getGanado_id()== ganado.getGanado_id()){
                    MedicinaDto medicinaDto = new MedicinaDto();
                    medicinaDto.setMedicina_id(medicina.getMedicina_id());
                    medicinaDto.setSintomas(medicina.getSintomas());
                    medicinaDto.setDiagnostico(medicina.getDiagnostico());
                    medicinaDto.setTratamiento(medicina.getTratamiento());
                    medicinaDto.setFecha_vacuna(medicina.getFecha_vacuna());
                    medicinaDto.setEstado(medicina.getEstado());
                    medicinaDto.setCodigoGanado(ganado.getCodigo());
                    medicinaDto.setNombreGanado(ganado.getNombre_ganado());
                    medicinaDto.setEstado_salud(medicina.getGanado().getEstado());
                    medicinaDto.setCreado(medicina.getCreado());
                    medicinaDto.setModificado(medicina.getModificado());
                    medicinasDto.add(medicinaDto);
                }
            }
        }
        return medicinasDto;
    }

    @Override
    public List<MedicinaDto> listaMedicinaPorEstado(String estado) {
        if(medicinaRepositorio.listaMedicinasPorEstado(estado).isEmpty()){
            throw new RuntimeException("No se encontraron medicinas con el estado: " + estado);
        }
        List<MedicinaDto> medicinasDto = new ArrayList<>();
        List<Ganado> ganados = ganadoRepositorio.findAll();
        for (Medicina medicina: medicinaRepositorio.listaMedicinasPorEstado(estado)) {
            for (Ganado ganado: ganados) {
                if(medicina.getGanado().getGanado_id()== ganado.getGanado_id()){
                    MedicinaDto medicinaDto = new MedicinaDto();
                    medicinaDto.setMedicina_id(medicina.getMedicina_id());
                    medicinaDto.setSintomas(medicina.getSintomas());
                    medicinaDto.setDiagnostico(medicina.getDiagnostico());
                    medicinaDto.setTratamiento(medicina.getTratamiento());
                    medicinaDto.setFecha_vacuna(medicina.getFecha_vacuna());
                    medicinaDto.setEstado(medicina.getEstado());
                    medicinaDto.setCodigoGanado(ganado.getCodigo());
                    medicinaDto.setNombreGanado(ganado.getNombre_ganado());
                    medicinaDto.setEstado_salud(medicina.getGanado().getEstado());
                    medicinaDto.setCreado(medicina.getCreado());
                    medicinaDto.setModificado(medicina.getModificado());
                    medicinasDto.add(medicinaDto);
                }
            }
        }
        return medicinasDto;
    }

    @Override
    public MedicinaDto buscarMedicinaPorId(int id) {
        if(medicinaRepositorio.findById(id).isPresent()){
            Medicina medicina = medicinaRepositorio.findById(id).get();
            MedicinaDto medicinaDto = new MedicinaDto();
            medicinaDto.setMedicina_id(medicina.getMedicina_id());
            medicinaDto.setSintomas(medicina.getSintomas());
            medicinaDto.setDiagnostico(medicina.getDiagnostico());
            medicinaDto.setTratamiento(medicina.getTratamiento());
            medicinaDto.setFecha_vacuna(medicina.getFecha_vacuna());
            medicinaDto.setEstado(medicina.getEstado());
            medicinaDto.setCodigoGanado(medicina.getGanado().getCodigo());
            medicinaDto.setNombreGanado(medicina.getGanado().getNombre_ganado());
            medicinaDto.setEstado_salud(medicina.getGanado().getEstado());
            medicinaDto.setCreado(medicina.getCreado());
            medicinaDto.setModificado(medicina.getModificado());
            return medicinaDto;
        }else{
            throw new RuntimeException("No se encontro la medicina con el id: " + id);
        }
    }
}
