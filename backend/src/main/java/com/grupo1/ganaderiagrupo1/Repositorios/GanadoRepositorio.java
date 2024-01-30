package com.grupo1.ganaderiagrupo1.Repositorios;
import com.grupo1.ganaderiagrupo1.Modelos.Ganado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GanadoRepositorio extends JpaRepository<Ganado, Integer> {
    //realizar una consulta jpa para buscar por estado y ordenar por id de forma ascendente
    @Query("SELECT g FROM Ganado g WHERE g.estado = ?1 ORDER BY g.ganado_id DESC")
    public List<Ganado> gandosPorEstadosAsc(String estado);
    @Query("SELECT g FROM Ganado g  ORDER BY g.ganado_id DESC")
    public List<Ganado> todosGanados();

    //buscar por el nombre del ganado comenzando con cualquier letra y ordenar por id de forma ascendente
    @Query("SELECT g FROM Ganado g WHERE g.nombre_ganado LIKE %?1% ORDER BY g.ganado_id DESC")
    public List<Ganado> buscarPorNombre(String nombre);

}
