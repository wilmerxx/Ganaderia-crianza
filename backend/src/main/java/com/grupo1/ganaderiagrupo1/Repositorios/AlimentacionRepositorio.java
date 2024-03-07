package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.Alimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AlimentacionRepositorio  extends JpaRepository<Alimentacion, Integer>{

    //realizar una consulta jpa para buscar por estado y ordenar por id de forma ascendente
    @Query("SELECT a FROM Alimentacion a WHERE a.estado = ?1 ORDER BY a.alimentacion_id DESC")
     List<Alimentacion> alimentacionPorEstadosAsc(String estado);

    @Query("SELECT a FROM Alimentacion a WHERE  a.estado ='Activo' ORDER BY a.alimentacion_id DESC")
     List<Alimentacion> todosAlimentacion();

    //se realiza una consulta jpa para buscar por id de forma ascendente
    @Query("SELECT a FROM Alimentacion a WHERE a.ganado.ganado_id = ?1 ORDER BY a.alimentacion_id DESC")
     List<Alimentacion> alimentacionPorGanado(int ganado_id);

    //lista de la suma de la cantidad de suplemento y nombre de suplemento
    @Query("SELECT a.nombre_suplemento, SUM(a.cantidad_suplemento) as total FROM Alimentacion a WHERE a.estado='Activo' GROUP BY a.nombre_suplemento")
     List<Object> listaAlimentacionTotalCantidad();
}
