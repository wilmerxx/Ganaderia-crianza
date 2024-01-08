package com.grupo1.ganaderiagrupo1.Repositorios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.stereotype.Repository;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class GanadoRepositorio {


    //List<Ganado> ganadoList = new ArrayList<>();
    List<Ganado> ganadoList = new ArrayList<>(
            List.of(
                    new Ganado("skf", "G23", "pepe", "Rosst", 34.5, "Macho", "12-12-2019", "Toro", "Activo"),
                    new Ganado("2", "2", "maria", "2", 345, "Hembra", "2018-12-12", "Vaca", "2"),
                    new Ganado("3", "3", "sofia", "3", 64, "Hembra", "2018-12-12", "Vaca", "3"),
                    new Ganado("4", "4", "julieta", "4", 456, "Hembra", "2018-12-12", "Ternero", "4"),
                    new Ganado("4", "4", "adria", "4", 456, "Macho", "2018-12-12", "Ternero", "4"),
                    new Ganado("5", "5", "coco", "5", 456, "Hembra", "2018-12-12", "Ternero", "5"),
                    new Ganado("6", "6", "matilde", "6", 456, "Hembra", "2018-12-12", "Vaca", "6"),
                    new Ganado("7", "7", "pablo", "7", 456, "Macho", "2018-12-12", "Toro", "7"),
                    new Ganado("8", "8", "lola", "8", 46, "Hembra", "2018-12-12", "Vaca", "8"),
                    new Ganado("9", "9", "camilo", "9", 456, "Macho", "2018-12-12", "Toro", "9"),
                    new Ganado("10", "10", "camila", "10", 456, "Hembra", "2018-12-12", "Vaca", "10"),
                    new Ganado("11", "11", "leo", "11", 456, "Macho", "2018-12-12", "Toro", "11"),
                    new Ganado("12", "12", "pancho", "12", 456, "Macho", "2018-12-12", "Toro", "12"),
                    new Ganado("13", "13", "rosalia", "13", 456, "Hembra", "2018-12-12", "Ternero", "13"),
                    new Ganado("14", "14", "sisa", "14", 45, "Hembra", "2018-12-12", "Vaca", "14"),
                    new Ganado("15", "15", "pedro", "15", 2, "Macho", "2018-12-12", "Toro", "15"),
                    new Ganado("16", "16", "silvia", "16", 5, "Hembra", "2018-12-12", "Vaca", "16")
            )
    );

    public void guardar(Object o) {
        ganadoList.add((Ganado) o);
    }

    public void cambiarEstado(Ganado o) {
        Ganado ganado = buscarPorId(o.getGanado_id());
        ganado.setEstado(o.getEstado());
    }

    public void actulizarGeneral(Ganado o){

        if(Objects.equals(o.getMadre().getGanado_id(), o.getGanado_id())){


        }


    }

    public void actualizar(Ganado o) {
        Ganado ganado = buscarPorId(o.getGanado_id());
        ganado.setGanado_id(o.getGanado_id());
        ganado.setNombre_ganado(o.getNombre_ganado());
        ganado.setRaza(o.getRaza());
        ganado.setSexo(o.getSexo());
        ganado.setPeso(o.getPeso());
        ganado.setFechaNacimiento(o.getFechaNacimiento());
        ganado.setEstado(o.getEstado());
        ganado.setCodigo(o.getCodigo());
        ganado.setTipo(o.getTipo());
        ganado.setMadre_id(o.getMadre_id());
        ganado.setPadre_id(o.getPadre_id());
        ganado.setPadre(buscarPorId(o.getPadre_id()));
        ganado.setMadre(buscarPorId(o.getMadre_id()));
    }

    public Ganado buscarPorId(String id) {
        if(ganadoList.isEmpty()){
            return null;
        }
        for (Ganado ganado: ganadoList) {
            if (ganado.getGanado_id().equals(id)){
                return ganado;
            }
        }
        return null;
    }

    public List<Ganado> buscarTodos() {
       return ganadoList;
    }

    public void eliminar(String id) {
        ganadoList.removeIf(ganado -> ganado.getGanado_id().equals(id));
    }


    public List<Ganado> buscarPorTipo(String tipo) {
        List<Ganado> ganadoList = new ArrayList<>();
        for (Ganado ganado: this.ganadoList) {
            if (ganado.getTipo().equals(tipo)){
                ganadoList.add(ganado);
            }
        }
        return ganadoList;
    }

    public List<Ganado> buscarPorNombre(String nombre) {
        List<Ganado> ganadoList = new ArrayList<>();
        for (Ganado ganado: this.ganadoList) {
            if (ganado.getNombre_ganado().contains(nombre)){
                ganadoList.add(ganado);
            }
        }
        return ganadoList;
    }
}
