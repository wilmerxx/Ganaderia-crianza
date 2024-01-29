package com.grupo1.ganaderiagrupo1.Repositorios;
import com.grupo1.ganaderiagrupo1.Modelos.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface  AreaRepository extends JpaRepository<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.estado = ?1 ORDER BY a.areaId DESC")
    public List<Area> areaPorEstadosAsc(String estado);

    @Query("SELECT a FROM Area a  ORDER BY a.areaId DESC")
    public List<Area> todosAreas();


}
