package com.grupo1.ganaderiagrupo1.Repositorios;


import com.grupo1.ganaderiagrupo1.Modelos.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
    public Users findByUsername(String username);

}
