package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Medicina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface MedicinaRepositorio extends JpaRepository<Medicina, Integer> {

    //lista de medicinas por estado y ordenadas por id descendente
    @Query(value = "SELECT * FROM medicina WHERE estado = ?1 ORDER BY medicina_id DESC", nativeQuery = true)
    List<Medicina> listaMedicinasPorEstado(String estado);

    //lista de medicinas por id descendente
    @Query(value = "SELECT * FROM medicina ORDER BY medicina_id DESC", nativeQuery = true)
    List<Medicina> listaMedicinas();

}
