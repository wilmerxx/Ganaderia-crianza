package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlEnfermedadesRepositorio extends JpaRepository<ControlEnfermedades, Integer> {

    //lista de controles por estado
    @Query(value = "SELECT * FROM control_enfermedades WHERE estado = ?1 ORDER BY control_id DESC", nativeQuery = true)
    List<ControlEnfermedades> listaControlEnfermedadesPorEstado(String estado);

    //lista de control por id desendente
    @Query(value = "SELECT * FROM control_enfermedades  ORDER BY control_id DESC", nativeQuery = true)
    List<ControlEnfermedades> listaControlEnfermedades();

}
