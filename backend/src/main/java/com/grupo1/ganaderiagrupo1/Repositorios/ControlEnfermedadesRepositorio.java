package com.grupo1.ganaderiagrupo1.Repositorios;

import com.grupo1.ganaderiagrupo1.Modelos.ControlEnfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

@Repository
public interface ControlEnfermedadesRepositorio extends JpaRepository<ControlEnfermedades, String> {


}
