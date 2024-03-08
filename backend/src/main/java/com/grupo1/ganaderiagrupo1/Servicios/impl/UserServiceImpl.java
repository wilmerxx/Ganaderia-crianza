package com.grupo1.ganaderiagrupo1.Servicios.impl;


import com.grupo1.ganaderiagrupo1.Modelos.Users;
import com.grupo1.ganaderiagrupo1.Repositorios.IUserRepository;
import com.grupo1.ganaderiagrupo1.Servicios.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

}
