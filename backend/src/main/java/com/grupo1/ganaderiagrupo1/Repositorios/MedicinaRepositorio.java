package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MedicinaRepositorio {
    Date date = new Date();

    List<Medicina> medicinaList = new ArrayList<>();

    /*List<Medicina> medicinaList = new ArrayList<>(
            List.of(
                    new Medicina("1","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("2","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("3","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("4","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("5","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("6","Garrapata", "Garrapata", "Garrapata", date, "100"),
                    new Medicina("7","Garrapata", "Garrapata", "Garrapata", date, "100")
            )
    );*/

    public List<Medicina> buscarTodos() {
        return medicinaList;
    }

    public Medicina buscarPorId(String id) {
        for (Medicina medicina: medicinaList) {
            if (medicina.getMedicina_id().equals(id)){
                return medicina;
            }
        }
        return null;
    }

    public void guardar(Medicina o) {
        medicinaList.add(o);
    }

    public void actualizar(Medicina o) {
        Medicina medicina = buscarPorId(o.getMedicina_id());
        medicina.setSintomas(o.getSintomas());
        medicina.setDiagnostico(o.getDiagnostico());
        medicina.setTratamiento(o.getTratamiento());
        medicina.setFecha_vacuna(o.getFecha_vacuna());
    }
}
