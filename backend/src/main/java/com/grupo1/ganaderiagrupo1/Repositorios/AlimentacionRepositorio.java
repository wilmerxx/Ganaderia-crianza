package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AlimentacionRepositorio {
    Date date = new Date();
    List<Alimentacion> alimentacionList = new ArrayList<>();

    /*List<Alimentacion> alimentacionList = new ArrayList<>(
            List.of(
                    new Alimentacion("1","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("2","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("3","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("4","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("5","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("6","Garrapata", "Garrapata", date, "100"),
                    new Alimentacion("7","Garrapata", "Garrapata", date, "100")
            )
    );*/

    public List<Alimentacion> buscarTodos() {
        return alimentacionList;
    }

    public Alimentacion buscarPorId(String id) {
        if (alimentacionList.isEmpty()){
            return null;
        }
        for (Alimentacion alimentacion: alimentacionList) {
            if (alimentacion.getAlimentacion_id().equals(id)){
                return alimentacion;
            }
        }
        return null;
    }

    public void guardar(Alimentacion o) {
        alimentacionList.add(o);
    }

    public void actualizar(Alimentacion o) {
        alimentacionList.removeIf(alimentacion -> alimentacion.getAlimentacion_id().equals(o.getAlimentacion_id()));
        alimentacionList.add(o);
    }

    //Calcular la cantidad de suplemento suministrado en un periodo de tiempo
    public double calcularCantidadSuplemento(Date fechaInicio, Date fechaFin) {
        double cantidadSuplemento = 0;
        for (Alimentacion alimentacion: alimentacionList) {
            if (alimentacion.getFecha_alimentacion().after(fechaInicio) && alimentacion.getFecha_alimentacion().before(fechaFin)){
                cantidadSuplemento += Double.parseDouble(alimentacion.getCantidad_suplemento());
            }
        }
        return cantidadSuplemento;
    }
}
