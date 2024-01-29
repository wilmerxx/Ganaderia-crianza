package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Reproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReproduccionRepository extends JpaRepository<Reproduccion, Integer> {

    //lista de reproducciones por estado y ordenadas por id descendente
    @Query(value = "SELECT * FROM reproduccion WHERE estado = ?1 ORDER BY reproduccion_id DESC", nativeQuery = true)
    List<Reproduccion> listaReproduccionesPorEstado(String estado);

    //lista de reproducciones por id descendente
    @Query(value = "SELECT * FROM reproduccion ORDER BY reproduccion_id DESC", nativeQuery = true)
    List<Reproduccion> listaReproducciones();

}
