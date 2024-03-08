package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Modelos.Users;
import com.grupo1.ganaderiagrupo1.Servicios.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Users user) {
       user.setPassword(bcrypt.encode(user.getPassword()));
       return ResponseEntity.ok(userService.save(user));
    }
}
